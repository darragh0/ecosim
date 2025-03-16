package ecosim.controller;

import java.util.List;

import ecosim.enm.Biome;
import ecosim.man.EcosystemMan;
import ecosim.misc.AnimalDescriptor;
import ecosim.misc.PlantDescriptor;
import ecosim.view.ActionsView;
import ecosim.view.EnvironmentView;
import ecosim.view.InputPromptView;
import ecosim.view.MapView;
import ecosim.view.ReportView;
import ecosim.view.SplashScreenView;

public class EcosystemController {

    private final EcosystemMan man;
    
    // Individual view components
    private final InputPromptView inputView;
    private final MapView mapView;
    private final ReportView reportView;
    private final ActionsView actionsView;
    private final EnvironmentView environmentView;

    public EcosystemController() {
        this.man = new EcosystemMan();
        
        // Initialize individual view components
        this.inputView = new InputPromptView();
        this.mapView = new MapView();
        this.reportView = new ReportView();
        this.actionsView = new ActionsView();
        this.environmentView = new EnvironmentView();
    }

    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::exit));
        this.showWelcomeScreen();
        this.setup();
        this.runSimulation();
    }

    private void showWelcomeScreen() {
        SplashScreenView.show();
        System.out.println("Welcome to the *Ecosystem Simulator* 🌳");
        System.out.println("To setup the ecosystem, please follow the prompts below.\n");
    }

    private void exit() {
        // Using console output directly for exit message
        System.out.println("\n[flr:(Simulator finished w/ exit code 0)]");
    }

    public void setup() {
        final Biome biome = this.inputView.promptBiomeSelection();
        this.man.setBiome(biome);

        final List<AnimalDescriptor> animals =
            this.inputView.promptAnimalSelection(this.man.getBiomeAnimals(), this.man.getInitialAnimals());
        final List<PlantDescriptor> plants =
            this.inputView.promptPlantSelection(this.man.getBiomePlants(), this.man.getInitialPlants());

        this.man.loadEcosystem(animals, plants, biome.name());
        this.man.populateMap();
        this.mapView.displayEcosytemMap(this.man);
    }

    public void runSimulation() {
        this.man.updateEnvironmentConditions();
        this.environmentView.displayEnvironmentConditions(this.man);
        
        // Set up action listener to display animal actions
        this.man.setActionListener(result -> this.actionsView.displayAnimalActions(result));
        
        this.actionsView.displayAnimalActionsHeader();
        for (int hour = 0; hour < 10; hour++) {
            if (hour == 5) {
                this.man.updateTimeOfDay();
                this.environmentView.displayTimeStatus(man);
            }
            this.man.processAnimalsTurn();
            this.man.checkOrganismsHealth();
        }
        
        this.mapView.displayEcosytemMap(this.man);
        this.reportView.displayDailyReport(this.man);
        this.man.resetNewAndDeadOrganisms();
    }
}