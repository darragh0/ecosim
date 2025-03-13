package ecosim.controller;


import java.util.List;

import ecosim.Environment;
import static ecosim.common.io.ConsoleIO.closeConsoleInputSource;
import static ecosim.common.io.ConsoleIO.prettyPrintln;
import ecosim.enm.Biome;
import ecosim.man.EcosystemMan;
import ecosim.organism.animal.Animal;
import ecosim.organism.plant.Plant;
import ecosim.view.EcosystemView;


public class EcosystemController {

    private final EcosystemMan man;
    private final EcosystemView view;

    public EcosystemController() {
        this.man = new EcosystemMan();
        this.view = new EcosystemView();
    }

    public void run() {
        // sample code of how the controller interacts with the model and view
        Runtime.getRuntime().addShutdownHook(new Thread(this::exit));
        this.view.showSplashScreen();
        this.setup();
        this.view.displayDailyReport(this.man);
        System.out.println("Starting simulation...");
    }

    private void exit() {
        int exitCode = 0;
        prettyPrintln("\n<B><r>[Simulator finished w/ exit code %d]</r></B>", exitCode);
        closeConsoleInputSource();
    }

    public void setup() {
        final Environment env = this.man.getEnvironment();
        final Biome biome = this.view.promptBiomeSelection();

        env.setBiome(biome);

        final List<Class<? extends Animal>> animals = this.view.promptAnimalSelection(env.getBiomeAnimals(), 3);
        final List<Class<? extends Plant>> plants = this.view.promptPlantSelection(env.getBiomePlants(), 3);

        this.man.loadEcosystem(animals, plants, biome.name());

        this.man.populateMap();
    }

}
