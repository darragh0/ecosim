package ecosim.ui.view;

/**
 * View responsible for displaying environment information.
 * Shows time, weather, seasons, and animal states.
 * @author Kabidoye-17
 */

import static ecosim.common.io.ConsoleIO.add;
import ecosim.game_engine.enm.ActivityState;
import ecosim.game_engine.man.EcosystemMan;

public class EnvironmentView {

    /**
     * Displays current time information and calls methods to show animal states
     * 
     * @param ecosystem The ecosystem manager containing time data
     */
    public void displayTimeStatus(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();
    
        add.accept(str, "**⏱️ [fly:Simulation Time] ⏱️**\n");
        add.accept(str, "  **Day:** [flc:%d]".formatted(ecosystem.getDayCount()));
        add.accept(str, "  **Time of Day:** [flc:%s] %s".formatted(
            ecosystem.getCurrentTimeOfDay().name(),
            ecosystem.getCurrentTimeOfDay().getIcon()));
        System.out.println(str.toString());
        this.displaySleepingAnimals(ecosystem);
        this.displayHibernatingAnimals(ecosystem);
    }
    
    /**
     * Displays current environment conditions (weather and season)
     * 
     * @param ecosystem The ecosystem manager containing environment data
     */
    public void displayEnvironmentConditions(EcosystemMan ecosystem) {  
        // Display environment conditions
        StringBuilder envStr = new StringBuilder();
        add.accept(envStr, "**🌍 [fly:Environment Status] 🌍**\n");
    
        add.accept(envStr, "  **Weather:** [flc:%s] %s".formatted(
            ecosystem.getCurrentWeather().name(),
            ecosystem.getCurrentWeather().getIcon()));
    
        add.accept(envStr, "  **Season:** [flc:%s] %s".formatted(
            ecosystem.getCurrentSeason().name(),
            ecosystem.getCurrentSeason().getIcon()));
    
        System.out.println(envStr.toString());
    }

    /**
     * Displays list of animals that are currently sleeping
     * 
     * @param ecosystem The ecosystem manager containing animal data
     */
    public void displaySleepingAnimals(EcosystemMan ecosystem) {
        var sleepingAnimals = ecosystem.getAnimals().stream()
            .filter(animal -> animal.getActivityState() == ActivityState.SLEEPING)
            .toList();
        
        if (!sleepingAnimals.isEmpty()) {
            StringBuilder str = new StringBuilder();
            
            add.accept(str, "**😴 [flm:Sleeping Animals] 😴**\n");
            
            sleepingAnimals.forEach(animal -> {
                add.accept(str, "  > %s".formatted(
                    animal.getName()));
            });
            
            System.out.println(str.toString());
        }
    }
    
    /**
     * Displays list of animals that are currently hibernating
     * 
     * @param ecosystem The ecosystem manager containing animal data
     */
    public void displayHibernatingAnimals(EcosystemMan ecosystem) {
        var hibernatingAnimals = ecosystem.getAnimals().stream()
            .filter(animal -> animal.getActivityState() == ActivityState.HIBERNATING)
            .toList();
        
        if (!hibernatingAnimals.isEmpty()) {
            StringBuilder str = new StringBuilder();
            
            add.accept(str, "**❄️ [flm:Hibernating Animals] ❄️**\n");
            
            hibernatingAnimals.forEach(animal -> {
                add.accept(str, "  > %s".formatted(
                    animal.getName()));
            });
            
            System.out.println(str.toString());
        }
    }
}