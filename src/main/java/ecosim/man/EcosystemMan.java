package ecosim.man;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import static ecosim.common.Util.randInt;
import ecosim.common.io.FileIO;
import ecosim.enm.Biome;
import ecosim.map.Map;
import ecosim.misc.EcosystemConfig;
import ecosim.organism.animal.Animal;
import ecosim.organism.animal.decorator.ConservationBoostDecorator;
import ecosim.organism.animal.decorator.FertilityBoostDecorator;
import ecosim.organism.animal.decorator.SurvivabilityBoostDecorator;
import ecosim.organism.animal.factory.AnimalFactoryProducer;
import ecosim.organism.plant.Plant;
import ecosim.organism.plant.factory.PlantFactoryProducer;


public class EcosystemMan {

    private final EnvironmentMan environment;
    private int dayCount;
    private final ArrayList<Animal> animals;
    private final ArrayList<Plant> plants;
    private final Map map;
    private final EcosystemConfig config;

    public EcosystemMan() {
        this.environment = new EnvironmentMan();
        this.dayCount = 0;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.map = Map.init(69, 69);
        this.config = this.loadConfig();
    }

    public void setup() {
        // TODO: implement the environment and biome setup
    }

    public void dailyUpdate() {
        // TODO: implement creating the loop for daily simulation
    }

    public void createPlant(Class<? extends Plant> plant, String biome) {
        Plant newPlant = PlantFactoryProducer.getFactory(biome).createPlant(plant);

        this.environment.registerTimeOfDayObservers(newPlant);
        this.environment.registerWeatherObservers(newPlant);
        this.plants.add(newPlant);
    }

    public void createAnimal(Class<? extends Animal> animal, String biome) {
        Animal newAnimal = AnimalFactoryProducer.getFactory(biome).createAnimal(animal);
        Animal decoratedAnimal = decorateAnimal(newAnimal);

        this.environment.registerTimeOfDayObservers(newAnimal);
        this.environment.registerSeasonObservers(newAnimal);
        this.animals.add(decoratedAnimal);
    }

    private Animal decorateAnimal(Animal animal) {
        final int randomNum = randInt(0, 3);
        return switch (randomNum) {
            case 0 -> new ConservationBoostDecorator(animal);
            case 1 -> new FertilityBoostDecorator(animal);
            case 2 -> new SurvivabilityBoostDecorator(animal);
            default -> {
                LoggerMan.log(Level.INFO, "Animal is returned as is (undecorated): " + animal.getName());
                yield animal;
            }
        };
    }

    public void loadEcosystem(List<Class<? extends Animal>> animals, List<Class<? extends Plant>> plants,
        String biome) {
        for (Class<? extends Animal> animal : animals) {
            this.createAnimal(animal, biome);
        }

        for (Class<? extends Plant> plant : plants) {
            this.createPlant(plant, biome);
        }
    }

    public void populateMap() {
        // Randomly place all organisms on the map during simulation setup
        this.animals.forEach(a -> this.map.initialisePlacement(a));
        this.plants.forEach(p -> this.map.initialisePlacement(p));
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

    public List<Class<? extends Animal>> getBiomeAnimals() {
        return this.environment.getBiomeAnimals();
    }

    public List<Class<? extends Plant>> getBiomePlants() {
        return this.environment.getBiomePlants();
    }

    public int getDayCount() {
        return this.dayCount;
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

    private EcosystemConfig loadConfig() {
        final Optional<EcosystemConfig> fileCfg = FileIO.parseEcosystemConfig();
        if (fileCfg.isEmpty()) {
            LoggerMan.log(Level.SEVERE, "Could not setup ecosystem controller");
            return null;
        }
        return fileCfg.get();
    }

    public int getInitialAnimals() {
        return this.config.initialAnimals();
    }

    public int getInitialPlants() {
        return this.config.initialPlants();
    }

}
