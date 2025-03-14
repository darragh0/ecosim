package ecosim.controller;


import java.util.List;

import ecosim.enm.Biome;
import ecosim.man.EcosystemMan;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;
import ecosim.view.EcosystemView;


public class EcosystemController {

    private final EcosystemMan man;
    private final EcosystemView view;

    public EcosystemController() {
        this.man = new EcosystemMan();
        this.view = new EcosystemView();
    }

    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::exit));
        this.view.welcome();
        this.setup();
        this.runSimulation();
        this.view.displayDailyReport(this.man);
    }

    private void exit() {
        this.view.end(0);
    }

    public void setup() {
        final Biome biome = this.view.promptBiomeSelection();
        this.man.setBiome(biome);

        final List<Class<? extends Animal>> animals =
            this.view.promptAnimalSelection(this.man.getBiomeAnimals(), this.man.getInitialAnimals());
        final List<Class<? extends Plant>> plants =
            this.view.promptPlantSelection(this.man.getBiomePlants(), this.man.getInitialPlants());

        this.man.loadEcosystem(animals, plants, biome.name());
        this.man.populateMap();
        this.view.displayEcosytemMap(this.man);
    }

    public void runSimulation() {
        this.man.updateEnvironmentConditions();
        this.view.displayEnvironmentConditions(this.man);
    }


}
