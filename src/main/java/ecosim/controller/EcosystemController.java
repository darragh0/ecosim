package ecosim.controller;

import ecosim.BiomeManager;
import ecosim.EcosystemManager;
import ecosim.Environment;
import ecosim.enm.Biome;
import ecosim.view.EcosystemView;

public class EcosystemController {
    private EcosystemManager ecosystemManager;
    private EcosystemView ecosystemView;

    public EcosystemController() {
        this.ecosystemManager = new EcosystemManager();
        this.ecosystemView = new EcosystemView();
    }

    public void run(){
        // sample code of how the controller interacts with the model and view
        this.setup();
        ecosystemView.displayDailyReport(ecosystemManager);
    }

    public void setup(){
        // beginning of setup
        Biome biome = ecosystemView.promptBiomeSelection();
        ecosystemManager.getEnvironment().setBiome(biome.getName());
    }
}
