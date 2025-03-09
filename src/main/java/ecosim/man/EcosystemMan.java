package ecosim.man;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import ecosim.Environment;
import static ecosim.common.Util.randInt;
import ecosim.map.Map;
import ecosim.organism.animal.Animal;
import ecosim.organism.animal.decorator.ConservationBoostDecorator;
import ecosim.organism.animal.decorator.FertilityBoostDecorator;
import ecosim.organism.animal.decorator.SurvivabilityBoostDecorator;
import ecosim.organism.animal.factory.AnimalFactory;
import ecosim.organism.animal.factory.AnimalFactoryProducer;
import ecosim.organism.plant.Plant;
import ecosim.organism.plant.factories.PlantFactory;
import ecosim.organism.plant.factories.PlantFactoryProducer;


public class EcosystemMan {

    private final Environment environment;
    private int dayCount;
    private final ArrayList<Animal> animals;
    private final ArrayList<Plant> plants;
    private final Map map;

    public EcosystemMan() {
        this.environment = new Environment();
        this.dayCount = 1;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.map = Map.init(69, 69);
    }

    public void setup() {
        // TODO: implement the environment and biome setup
    }

    public void dailyUpdate() {
        // TODO: implement creating the loop for daily simulation
    }

    public void createAnimal(String animal, String biome) {
        AnimalFactory animalFactory = AnimalFactoryProducer.getFactory(biome);
        Animal newAnimal = animalFactory.createAnimal(animal);
        Animal decoratedAnimal = decorateAnimal(newAnimal);

        // register with the Season and TimeOfDay observables
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

    public void createPlant(String plant, String biome) {
        PlantFactory plantFactory = PlantFactoryProducer.getFactory(biome);
        Plant newPlant = plantFactory.createPlant(plant);

        // register with the Weather and TimeOfDay observable
        this.environment.registerTimeOfDayObservers(newPlant);
        this.environment.registerWeatherObservers(newPlant);

        this.plants.add(newPlant);

    }

    public void populateMap() {
        // Randomly place all organisms on the map during simulation setup
        this.animals.forEach(a -> this.map.initialisePlacement(a));
        this.plants.forEach(p -> this.map.initialisePlacement(p));
    }

    public Environment getEnvironment() {
        return this.environment;
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

}
