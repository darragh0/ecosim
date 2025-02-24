package ecosim;
import ecosim.organism.animal.Animal;
import ecosim.organism.plant.Plant;

import java.util.ArrayList;
import java.util.List;

import static ecosim.util.io.ConsoleIO.closeConsoleInputSource;
import static ecosim.util.io.ConsoleIO.prettyPrintln;

public class EcosystemManager {

    private final Environment environment;
    private int dayCount;
    private ArrayList<Animal> animals;
    private ArrayList<Plant> plants;

    public EcosystemManager() {
        this.environment = new Environment();
        this.dayCount = 1; // starts on day 1
        this.animals = new ArrayList<Animal>();
        this.plants = new ArrayList<Plant>();
    }

    public void setup() {
        // TODO: implement the environment and biome setup
    }

    public void dailyUpdate(){
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

    public void createAnimal() {
        // TODO: implement creating an animal based off of the factory
    }

    public void createPlant() {
        // TODO: implement creating a plant based off of the factory
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
