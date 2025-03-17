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

    private  void processAnimalsTurn() {
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

    private  void processPlantsTurn(){
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
                p.adjustHealth(-healthReduction);

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

    public void processOrganismsTurn() {
        this.processAnimalsTurn();
        this.processPlantsTurn();
    }

    public void resetNewAndDeadOrganisms() {
        this.deadAnimals.clear();
        this.deadPlants.clear();
        this.newbornAnimals.clear();
        this.newbornPlants.clear();
    }

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
        // Get the appropriate factory for this biome
    public void loadEcosystem(List<AnimalDescriptor> animals, List<PlantDescriptor> plants, String biome) {

        for (AnimalDescriptor animal : animals) {
            this.createAnimal(animal, biome);
        }

        for (PlantDescriptor plant : plants) {
            this.createPlant(plant, biome);
        }
    }

    public MapSize getMapSize() {
        return map.getMapDimensions();
    }

    public Grid getMapGrid() {
        return this.map.getGrid();
    }

    public void populateMap() {
        // Randomly place all organisms on the map during simulation setup
        this.animals.forEach(a -> this.map.initialisePlacement(a));
        this.plants.forEach(p -> this.map.initialisePlacement(p));
    }

    public boolean isEcosystemAlive() {
        return !this.animals.isEmpty() && !this.plants.isEmpty();
    }

    public boolean isAtMaxCapacity() {
        return this.animals.size() + this.plants.size() >= this.config.maxCapacity();
    }
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

    public void setBiome(Biome biome) {
        this.environment.setBiome(biome);
    }

    public List<AnimalDescriptor> getBiomeAnimals() {
        return this.environment.getBiomeAnimals();
    }

    public List<PlantDescriptor> getBiomePlants() {
        return this.environment.getBiomePlants();
    }

    public int getDayCount() {
        return this.dayCount;
    }

    public void updateTimeOfDay() {
        this.environment.updateTimeOfDay();
    }

    public Season getCurrentSeason() {
        return this.environment.getSeason();
    }

    public Weather getCurrentWeather() {
        return this.environment.getWeather();
    }

    public TimeOfDay getCurrentTimeOfDay() {
        return this.environment.getTimeOfDay();
    }

    public int getAnimalCount() {
        return this.animals.size();
    }

    public int getPlantCount() {
        return this.plants.size();
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public List<Plant> getPlants() {
        return this.plants;
    }

    public List<Animal> getDeadAnimals() {
        return this.deadAnimals;
    }

    public List<Plant> getDeadPlants() {
        return this.deadPlants;
    }

    public List<Animal> getNewbornAnimals() {
        return this.newbornAnimals;
    }

    public List<Plant> getNewbornPlants() {
        return this.newbornPlants;
    }

    public int getTotalDeadAnimals() {
        return this.totalDeadAnimals;
    }

    public int getTotalDeadPlants() {
        return this.totalDeadPlants;
    }

    public int getTotalNewbornAnimals() {
        return this.totalNewbornAnimals;
    }   

    public int getTotalNewbornPlants() {
        return this.totalNewbornPlants;
    }   

    private EcosystemConfig loadConfig() {
        final Optional<EcosystemConfig> fileCfg = FileIO.parseEcosystemConfig();
        if (fileCfg.isEmpty()) {
            LoggerMan.log(Level.SEVERE, "Could not setup ecosystem controller");
            return null;
        }
        return fileCfg.get();
    }

    public void setActionListener(ActionResultListener listener) {
        this.actionListener = listener;
    }


    public int getInitialAnimals() {
        return this.config.initialAnimals();
    }
    
    public int getMaxDays() {
        return this.config.maxDays();
    }

    public int getHoursPerDay() {
        return this.config.hoursPerDay();
    }

    public int getInitialPlants() {
        return this.config.initialPlants();
    }

}
