package ecosim;


import static ecosim.util.io.ConsoleIO.closeConsoleInputSource;
import static ecosim.util.io.ConsoleIO.prettyPrintln;


public class EcosystemManager {

    private final Environment environment;
    private int dayCount;

    public EcosystemManager() {
        this.environment = new Environment();
        this.dayCount = 1;
    }

    public void setup() {
        // TODO: implement the environment and biome setup
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

    public void dailyUpdate() {
        // TODO: implement creating the loop for daily simulation
    }

    public void generateDailyReport() {
        // TODO: implement displaying daily info on animals, plants and environment
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

}
