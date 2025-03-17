package ecosim.game_engine.man;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import ecosim.common.io.FileIO;
import ecosim.game_engine.enm.Biome;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.enm.Weather;
import ecosim.game_engine.map.ActionResult;
import ecosim.game_engine.map.ActionResult.ActionType;
import static ecosim.game_engine.map.ActionResult.ActionType.SUCCESSFUL_EATING;
import ecosim.game_engine.map.Grid;
import ecosim.game_engine.map.Map;
import ecosim.game_engine.map.Map.MapSize;
import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.EcosystemConfig;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.animal.abs.Animal;
import ecosim.game_engine.organism.builder.AnimalBuilder;
import ecosim.game_engine.organism.builder.PlantBuilder;
import ecosim.game_engine.organism.factory.BiomeOrganismFactory;
import ecosim.game_engine.organism.factory.BiomeOrganismFactoryProvider;
import ecosim.game_engine.organism.plant.abs.Plant;
import ecosim.ui.view.ActionResultListener;

/**
 * Central manager class for the entire ecosystem simulation.
 * Coordinates organisms, environment, and simulation lifecycle.
 * Handles creation, movement, interaction, and lifecycle of all organisms.
 * @author Kabidoye-17
 */
public class EcosystemMan {

    private final EnvironmentMan environment;
    private int dayCount;
    private final ArrayList<Animal> animals;
    private final ArrayList<Plant> plants;
    private final List<Animal> deadAnimals;
    private final List<Plant> deadPlants;
    private int totalDeadAnimals;
    private int totalDeadPlants;
    private final List<Animal> newbornAnimals;
    private final List<Plant> newbornPlants;
    private int totalNewbornAnimals;
    private int totalNewbornPlants;
    private final Map map;
    private final EcosystemConfig config;

    private ActionResultListener actionListener;

    /**
     * Creates a new ecosystem manager with default settings.
     * Initializes the environment, organism lists, and map.
     */
    public EcosystemMan() {
        this.environment = new EnvironmentMan();
        this.dayCount = 0;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.deadAnimals = new ArrayList<>();
        this.deadPlants = new ArrayList<>();
        this.totalDeadAnimals = 0;
        this.totalDeadPlants = 0;
        this.newbornAnimals = new ArrayList<>();
        this.newbornPlants = new ArrayList<>();
        this.totalNewbornAnimals = 0;
        this.totalNewbornPlants = 0;
        this.map = Map.init(8, 8);
        this.config = this.loadConfig();
    }

    /**
     * Processes a turn for all animals in the ecosystem.
     * Handles movement, breeding, eating, and other actions.
     */
    private void processAnimalsTurn() {
        // Create a copy of the animals list to safely iterate through
        List<Animal> currentAnimals = new ArrayList<>(this.animals);

        for (Animal a : currentAnimals) {
            // Skip animals that have already been removed
            if (!this.animals.contains(a)) {
                continue;
            }

            ActionResult result = a.move();
            ActionType actionType = result.getActionType();

            // Handle new offspring from breeding
            if (actionType == ActionType.SUCCESSFUL_BREEDING && result.getOffspring() != null) {
                Animal offspring = result.getOffspring();
                this.animals.add(offspring);
                this.newbornAnimals.add(offspring);
                this.totalNewbornAnimals++;

                // Register the new animal with environment observers
                this.environment.registerTimeOfDayObservers(offspring);
                this.environment.registerSeasonObservers(offspring);
            }

            if (actionType == SUCCESSFUL_EATING) {
                switch (result.getTarget()) {
                    case Plant plant -> {
                        if (plant.isDead()) {
                            this.plants.remove(plant);
                            this.deadPlants.add(plant);
                            this.totalDeadPlants++;
                        }
                    }
                    case Animal animal -> {
                        this.animals.remove(animal);
                        this.deadAnimals.add(animal);
                        this.totalDeadAnimals++;
                    }
                    default -> LoggerMan.log(Level.WARNING, "Unknown target type: " + result.getTarget());
                }
            }

            if (actionListener != null) {
                actionListener.onActionPerformed(result);
            }
        }
    }

    /**
     * Processes a turn for all plants in the ecosystem.
     * Handles reproduction, energy cycle, and other plant-specific actions.
     */
    private void processPlantsTurn(){
        // Create a copy of the plants list to safely iterate through
        List<Plant> currentPlants = new ArrayList<>(this.plants);
    
        for (Plant p : currentPlants) {
            // Skip plants that have already been removed
            if (!this.plants.contains(p)) {
                continue;
            }
            
            // Create a basic action result by default
            ActionResult result = new ActionResult(
                ActionType.BASIC_ACTION,
                p, null, p.getX(), p.getY());
                
            if (p.canReproduce()) {
                float healthReduction = (p.getHealth() * 0.3f);
                p.reduceHealth(healthReduction);

                Plant newPlant = p.performAsexualReproduction();
                if (newPlant != null) {
                    this.plants.add(newPlant);
                    this.map.initialisePlacement(newPlant);
                    this.newbornPlants.add(newPlant);
                    this.totalNewbornPlants++;
                    
                    // Create successful breeding action result
                    result = new ActionResult(
                        ActionType.SUCCESSFUL_BREEDING,
                        p, null, p.getX(), p.getY(), null);
                }
            }
    
            p.performEnergyCycle();
    
            // Only notify if it's not a basic action
            if (actionListener != null && result.getActionType() != ActionType.BASIC_ACTION) {
                actionListener.onActionPerformed(result);
            }
        }
    }

    /**
     * Processes a turn for all organisms in the ecosystem.
     * Calls individual turn processors for animals and plants.
     */
    public void processOrganismsTurn() {
        this.processAnimalsTurn();
        this.processPlantsTurn();
    }

    /**
     * Clears the lists of new and dead organisms.
     * Called at the end of a simulation cycle.
     */
    public void resetNewAndDeadOrganisms() {
        this.deadAnimals.clear();
        this.deadPlants.clear();
        this.newbornAnimals.clear();
        this.newbornPlants.clear();
    }

    /**
     * Checks the health of all organisms and removes dead ones.
     * Notifies listeners of organism deaths.
     */
    public void checkOrganismsHealth() {

        List<Animal> animalsToRemove = new ArrayList<>();

        for (Animal animal : this.animals) {
            if (animal.getHealth() <= 0) {
                animalsToRemove.add(animal);
            }
        }
        for (Animal animal : animalsToRemove) {
            this.map.getGrid().rmv(animal);
            this.animals.remove(animal);
            this.deadAnimals.add(animal);
            this.totalDeadAnimals++;

            if (actionListener != null) {
                ActionResult deathResult = new ActionResult(
                    ActionType.DIED,
                    animal, null,
                    animal.getX(), animal.getY());
                actionListener.onActionPerformed(deathResult);
            }
        }

        List<Plant> plantsToRemove = new ArrayList<>();

        for (Plant plant : this.plants) {
            if (plant.getHealth() <= 0) {
                plantsToRemove.add(plant);
            }
        }

        for (Plant plant : plantsToRemove) {
            this.map.getGrid().rmv(plant);
            this.plants.remove(plant);
            this.deadPlants.add(plant);
            this.totalDeadPlants++;

            if (actionListener != null) {
                ActionResult deathResult = new ActionResult(
                    ActionType.DIED,
                    plant, null, plant.getX(), plant.getY());
                actionListener.onActionPerformed(deathResult);
            }
        }

    }

    /**
     * Creates an animal from a descriptor and adds it to the ecosystem.
     * Uses the appropriate biome factory to build the animal.
     * 
     * @param descriptor The animal descriptor
     * @param biomeName The name of the biome
     */
    private void createAnimal(AnimalDescriptor descriptor, String biomeName) {
    try{
        Biome biome = Biome.valueOf(biomeName);

        BiomeOrganismFactory factory = BiomeOrganismFactoryProvider.getFactory(biome);
    
    // Use the factory to get a builder
        AnimalBuilder builder = factory.createAnimalBuilder(descriptor);
        
        // Build the animal with basic properties and apply decorators
        Animal animal = builder
            .buildBasicProperties()
            .applyDecorators()
            .build();
        
        // Register observers and add to the ecosystem
        this.environment.registerTimeOfDayObservers(animal);
            this.environment.registerSeasonObservers(animal);
            this.animals.add(animal);
        } catch (IllegalArgumentException e) {
            LoggerMan.log(Level.SEVERE, "Invalid biome name: " + biomeName);
        }
    }
    
    /**
     * Creates a plant from a descriptor and adds it to the ecosystem.
     * Uses the appropriate biome factory to build the plant.
     * 
     * @param descriptor The plant descriptor
     * @param biomeName The name of the biome
     */
    public void createPlant(PlantDescriptor descriptor, String biomeName) {
        try{
            Biome biome = Biome.valueOf(biomeName);

            BiomeOrganismFactory factory = BiomeOrganismFactoryProvider.getFactory(biome);
            
            // Use the factory to get a builder
            PlantBuilder builder = factory.createPlantBuilder(descriptor);
            
            // Build the plant with basic properties
            Plant plant = builder
                .buildBasicProperties()
                .build();
            
            // Register observers and add to the ecosystem
            this.environment.registerTimeOfDayObservers(plant);
            this.environment.registerWeatherObservers(plant);
            this.plants.add(plant);
        } catch (IllegalArgumentException e) {
            LoggerMan.log(Level.SEVERE, "Invalid biome name: " + biomeName);
        }
    }

    /**
     * Loads organisms into the ecosystem from descriptor lists.
     * 
     * @param animals List of animal descriptors
     * @param plants List of plant descriptors
     * @param biome Name of the biome
     */
    public void loadEcosystem(List<AnimalDescriptor> animals, List<PlantDescriptor> plants, String biome) {

        for (AnimalDescriptor animal : animals) {
            this.createAnimal(animal, biome);
        }

        for (PlantDescriptor plant : plants) {
            this.createPlant(plant, biome);
        }
    }

    /**
     * Gets the current map size.
     * 
     * @return The map size enum
     */
    public MapSize getMapSize() {
        return map.getMapDimensions();
    }

    /**
     * Gets the grid representation of the map.
     * 
     * @return The grid object
     */
    public Grid getMapGrid() {
        return this.map.getGrid();
    }

    /**
     * Places all organisms on the map at random positions.
     * Called during ecosystem initialization.
     */
    public void populateMap() {
        // Randomly place all organisms on the map during simulation setup
        this.animals.forEach(a -> this.map.initialisePlacement(a));
        this.plants.forEach(p -> this.map.initialisePlacement(p));
    }

    /**
     * Checks if the ecosystem is still viable.
     * An ecosystem requires both animals and plants to be alive.
     * 
     * @return True if the ecosystem is alive
     */
    public boolean isEcosystemAlive() {
        return !this.animals.isEmpty() && !this.plants.isEmpty();
    }

    /**
     * Checks if the ecosystem has reached maximum capacity.
     * 
     * @return True if at max capacity
     */
    public boolean isAtMaxCapacity() {
        return this.animals.size() + this.plants.size() >= this.config.maxCapacity();
    }

    /**
     * Updates all environmental conditions.
     * Advances day count and updates season, time of day, and weather.
     */
    public void updateEnvironmentConditions() {
        // Increment day count first
        this.dayCount++;

        // Check if it's time to change the season (every 5 days)
        if (this.dayCount == 1 || this.dayCount % 5 == 0) {
            this.environment.updateSeason();
        }

        this.environment.updateTimeOfDay();
        this.environment.updateWeather();
    }

    /**
     * Sets the biome for the ecosystem.
     * 
     * @param biome The biome to set
     */
    public void setBiome(Biome biome) {
        this.environment.setBiome(biome);
    }

    /**
     * Gets the list of animals available for the current biome.
     * 
     * @return List of animal descriptors
     */
    public List<AnimalDescriptor> getBiomeAnimals() {
        return this.environment.getBiomeAnimals();
    }

    /**
     * Gets the list of plants available for the current biome.
     * 
     * @return List of plant descriptors
     */
    public List<PlantDescriptor> getBiomePlants() {
        return this.environment.getBiomePlants();
    }

    /**
     * Gets the current day count.
     * 
     * @return The day count
     */
    public int getDayCount() {
        return this.dayCount;
    }

    /**
     * Updates the time of day.
     */
    public void updateTimeOfDay() {
        this.environment.updateTimeOfDay();
    }

    /**
     * Gets the current season.
     * 
     * @return The current season
     */
    public Season getCurrentSeason() {
        return this.environment.getSeason();
    }

    /**
     * Gets the current weather.
     * 
     * @return The current weather
     */
    public Weather getCurrentWeather() {
        return this.environment.getWeather();
    }

    /**
     * Gets the current time of day.
     * 
     * @return The current time of day
     */
    public TimeOfDay getCurrentTimeOfDay() {
        return this.environment.getTimeOfDay();
    }

    /**
     * Gets the count of living animals.
     * 
     * @return The animal count
     */
    public int getAnimalCount() {
        return this.animals.size();
    }

    /**
     * Gets the count of living plants.
     * 
     * @return The plant count
     */
    public int getPlantCount() {
        return this.plants.size();
    }

    /**
     * Gets the list of living animals.
     * 
     * @return List of animals
     */
    public List<Animal> getAnimals() {
        return this.animals;
    }

    /**
     * Gets the list of living plants.
     * 
     * @return List of plants
     */
    public List<Plant> getPlants() {
        return this.plants;
    }

    /**
     * Gets the list of animals that died in the current cycle.
     * 
     * @return List of dead animals
     */
    public List<Animal> getDeadAnimals() {
        return this.deadAnimals;
    }

    /**
     * Gets the list of plants that died in the current cycle.
     * 
     * @return List of dead plants
     */
    public List<Plant> getDeadPlants() {
        return this.deadPlants;
    }

    /**
     * Gets the list of animals born in the current cycle.
     * 
     * @return List of newborn animals
     */
    public List<Animal> getNewbornAnimals() {
        return this.newbornAnimals;
    }

    /**
     * Gets the list of plants born in the current cycle.
     * 
     * @return List of newborn plants
     */
    public List<Plant> getNewbornPlants() {
        return this.newbornPlants;
    }

    /**
     * Gets the total count of animals that have died.
     * 
     * @return Total dead animals
     */
    public int getTotalDeadAnimals() {
        return this.totalDeadAnimals;
    }

    /**
     * Gets the total count of plants that have died.
     * 
     * @return Total dead plants
     */
    public int getTotalDeadPlants() {
        return this.totalDeadPlants;
    }

    /**
     * Gets the total count of animals that have been born.
     * 
     * @return Total newborn animals
     */
    public int getTotalNewbornAnimals() {
        return this.totalNewbornAnimals;
    }   

    /**
     * Gets the total count of plants that have been born.
     * 
     * @return Total newborn plants
     */
    public int getTotalNewbornPlants() {
        return this.totalNewbornPlants;
    }   

    /**
     * Loads the ecosystem configuration from file.
     * 
     * @return The ecosystem configuration
     */
    private EcosystemConfig loadConfig() {
        final Optional<EcosystemConfig> fileCfg = FileIO.parseEcosystemConfig();
        if (fileCfg.isEmpty()) {
            LoggerMan.log(Level.SEVERE, "Could not setup ecosystem controller");
            return null;
        }
        return fileCfg.get();
    }

    /**
     * Sets the action listener for ecosystem events.
     * 
     * @param listener The listener to set
     */
    public void setActionListener(ActionResultListener listener) {
        this.actionListener = listener;
    }

    /**
     * Gets the initial animal count from config.
     * 
     * @return Initial animal count
     */
    public int getInitialAnimals() {
        return this.config.initialAnimals();
    }
    
    /**
     * Gets the maximum days for simulation from config.
     * 
     * @return Maximum days
     */
    public int getMaxDays() {
        return this.config.maxDays();
    }

    /**
     * Gets hours per day from config.
     * 
     * @return Hours per day
     */
    public int getHoursPerDay() {
        return this.config.hoursPerDay();
    }

    /**
     * Gets initial plant count from config.
     * 
     * @return Initial plant count
     */
    public int getInitialPlants() {
        return this.config.initialPlants();
    }

}
