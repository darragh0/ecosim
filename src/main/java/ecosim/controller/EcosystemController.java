package ecosim.controller;


import java.util.List;

import ecosim.Environment;
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
        this.view.welcome();
        this.setup();
        this.view.displayDailyReport(this.man);
    }

    private void exit() {
        this.view.end(0);
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
