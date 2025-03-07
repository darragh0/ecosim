package ecosim.man;


import ecosim.Environment;
import ecosim.SplashScreen;
import ecosim.organism.animal.Animal;
import ecosim.organism.plant.Plant;
import ecosim.organism.plant.factories.PlantFactory;
import ecosim.organism.plant.factories.PlantFactoryProducer;

import static ecosim.common.io.ConsoleIO.closeConsoleInputSource;
import static ecosim.common.io.ConsoleIO.prettyPrintln;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static ecosim.common.Util.randInt;
import ecosim.organism.animal.decorator.ConservationBoostDecorator;
import ecosim.organism.animal.decorator.FertilityBoostDecorator;
import ecosim.organism.animal.decorator.SurvivabilityBoostDecorator;
import ecosim.organism.animal.factory.AnimalFactory;
import ecosim.organism.animal.factory.AnimalFactoryProducer;


public class EcosystemMan {

    private final Environment environment;
    private int dayCount;
    private ArrayList<Animal> animals;
    private ArrayList<Plant> plants;

    public EcosystemMan() {
        this.environment = new Environment();
        this.dayCount = 1; // starts on day 1
        this.animals = new ArrayList<Animal>();
        this.plants = new ArrayList<Plant>();
    }

    public void setup() {
        // TODO: implement the environment and biome setup
    }

    public void dailyUpdate() {
        // TODO: implement creating the loop for daily simulation
    }

    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::exit));
        SplashScreen.show();
        System.out.println("Starting simulation...");

        // simulator loop here
    }

    public void exit() {
        int exitCode = 0;
        prettyPrintln("\n<B><r>[Simulator finished w/ exit code %d]</r></B>", exitCode);
        closeConsoleInputSource();
    }

    public void createAnimal(String animal, String biome) {
        AnimalFactory animalFactory = AnimalFactoryProducer.getFactory(biome);
        Animal newAnimal = animalFactory.createAnimal(animal);
        Animal decoratedAnimal = decorateAnimal(newAnimal);
        animals.add(decoratedAnimal);

    }


    private Animal decorateAnimal(Animal animal) {
        int randomNum = randInt(0, 3); 
        Animal decoratedAnimal = animal; 
    
        switch (randomNum) {
            case 0 -> decoratedAnimal = new ConservationBoostDecorator(animal); 
            case 1 -> decoratedAnimal = new FertilityBoostDecorator(animal);    
            case 2 -> decoratedAnimal = new SurvivabilityBoostDecorator(animal); 
            default -> {
               LoggerMan.log(Level.INFO, "Animal is returned as is (undecorated): " + animal.getName());
            }
        }
    
        return decoratedAnimal;
    }
    public void createPlant(String plant, String biome) {
        PlantFactory plantFactory = PlantFactoryProducer.getFactory(biome);
        Plant newPlant = plantFactory.createPlant(plant);
        plants.add(newPlant);

    }

    public void populateMap() {
        // TODO: implement populating the map and environment with plants and animals
    }

    public Environment getEnvironment() {
        return environment;
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
