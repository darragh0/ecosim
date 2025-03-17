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


/**
 * Main controller for the ecosystem simulation.
 * Coordinates interactions between the ecosystem manager and view components.
 * Handles simulation setup, execution, and user interface flow.
 * @author Kabidoye-17
 */



public class EcosystemController {

    private final EcosystemMan man;

    // Individual view components
    private final InputPromptView inputView;
    private final MapView mapView;
    private final ReportView reportView;
    private final ActionsView actionsView;
    private final EnvironmentView environmentView;

    /**
     * Creates a new ecosystem controller.
     * Initializes the ecosystem manager and all view components.
     */
    public EcosystemController() {
        this.man = new EcosystemMan();

        // Initialize individual view components
        this.inputView = new InputPromptView();
        this.mapView = new MapView();
        this.reportView = new ReportView();
        this.actionsView = new ActionsView();
        this.environmentView = new EnvironmentView();
    }

    /**
     * Main entry point for running the simulation.
     * Shows welcome screen, performs setup, and runs the simulation.
     */
    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::exit));
        this.showWelcomeScreen();
        this.setup();
        this.runSimulation();
    }

    /**
     * Displays the welcome screen to the user.
     * Shows splash screen and introductory messages.
     */
    private void showWelcomeScreen() {
        SplashScreenView.show();
        pprintln("Welcome to the *Ecosystem Simulator* 🌳");
        pprintln("To setup the ecosystem, please follow the prompts below.\n");
    }

    /**
     * Handles graceful exit of the application.
     * Displays exit message with status code.
     */
    private void exit() {
        pprintln("\n[flr:(Simulator finished w/ exit code 0)]");
    }

    /**
     * Sets up the ecosystem based on user input.
     * Prompts for biome, animal, and plant selection.
     */
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

    /**
     * Runs the main simulation loop.
     * Processes simulation days until termination conditions are met.
     * Displays ecosystem status and reports after each day.
     */
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


