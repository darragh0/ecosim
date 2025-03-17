package ecosim.controller;


import static ecosim.common.io.ConsoleIO.pprintln;

import java.util.List;

import ecosim.game_engine.enm.Biome;
import ecosim.game_engine.man.EcosystemMan;
import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.ui.view.ActionsView;
import ecosim.ui.view.EnvironmentView;
import ecosim.ui.view.InputPromptView;
import ecosim.ui.view.MapView;
import ecosim.ui.view.ReportView;
import ecosim.ui.view.SplashScreenView;


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
        pprintln("Welcome to the *Ecosystem Simulator* 🌳");
        pprintln("To setup the ecosystem, please follow the prompts below.\n");
    }

    private void exit() {
        pprintln("\n[flr:(Simulator finished w/ exit code 0)]");
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
        while (this.man.getDayCount() < man.getMaxDays() && this.man.isEcosystemAlive()
            && !this.man.isAtMaxCapacity()) {
            this.man.updateEnvironmentConditions();
            this.environmentView.displayEnvironmentConditions(this.man);
            this.environmentView.displayTimeStatus(man);

            // Set up action listener to display animal actions
            this.man.setActionListener(result -> this.actionsView.displayAnimalActions(result));

            this.actionsView.displayAnimalActionsHeader();
            for (int hour = 0; hour < man.getHoursPerDay(); hour++) {
                if (hour == man.getHoursPerDay() / 2) {
                    this.man.updateTimeOfDay();
                    this.environmentView.displayTimeStatus(man);
                }
                this.man.processOrganismsTurn();
                this.man.checkOrganismsHealth();
            }

            this.mapView.displayEcosytemMap(this.man);
            this.reportView.displayDailyReport(this.man);
            this.man.resetNewAndDeadOrganisms();
        }
        this.reportView.displayFinalReport(man);
    }

}
