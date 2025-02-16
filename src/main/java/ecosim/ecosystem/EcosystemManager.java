package ecosim.ecosystem;

public class EcosystemManager {
    private final Environment environment;
    private int dayCount;

    public EcosystemManager() {
        this.environment = new Environment();
        this.dayCount = 1;
    }

    public void setup(){
        // TODO: implement the environment and biome setup
    }

    public void runSimulation(){
        // TODO: implement creating the main simulation to run everything
    }

    public void dailyUpdate(){
        // TODO: implement creating the loop for daily simulation
    }

    public void generateDailyReport(){
        // TODO: implement displaying daily info on animals, plants and environment
    }

    public void createAnimal(){
        // TODO: implement creating an animal based off of the factory
    }

    public void createPlant(){
        // TODO: implement creating a plant based off of the factory
    }

    public void populateMap(){
        // TODO: implement populating the map and environment with plants and animals

    }




}
