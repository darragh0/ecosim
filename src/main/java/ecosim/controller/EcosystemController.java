package ecosim.controller;


import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import ecosim.common.io.FileIO;
import ecosim.enm.Biome;
import ecosim.man.EcosystemMan;
import ecosim.man.LoggerMan;
import ecosim.misc.EcosystemConfig;
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

        final Optional<EcosystemConfig> fileCfg = FileIO.parseEcosystemConfig();
        if (fileCfg.isEmpty()) {
            LoggerMan.log(Level.SEVERE, "Could not setup ecosystem controller");
            return;
        }
        EcosystemConfig cfg = fileCfg.get();

        final List<Class<? extends Animal>> animals =
            this.view.promptAnimalSelection(this.man.getBiomeAnimals(), cfg.initialAnimals());
        final List<Class<? extends Plant>> plants =
            this.view.promptPlantSelection(this.man.getBiomePlants(), cfg.initialPlants());

        this.man.loadEcosystem(animals, plants, biome.name());

        this.man.populateMap();
    }

    public void runSimulation() {
        this.man.updateEnvironmentConditions();
    }


}
