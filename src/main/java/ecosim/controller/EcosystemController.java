package ecosim.controller;

import ecosim.EcosystemManager;
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
        ecosystemView.displayDailyReport(ecosystemManager.generateDailyReport());
    }
}
