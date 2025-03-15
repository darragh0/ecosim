package ecosim.view;

import static ecosim.common.io.ConsoleIO.add;
import ecosim.man.EcosystemMan;

public class EnvironmentView {


     public void displayTimeStatus(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();

        add.accept(str, "**⏱️ [fly:Simulation Time] ⏱️**\n");
        add.accept(str, "  **Day:** [flc:%d]".formatted(ecosystem.getDayCount()));
        add.accept(str, "  **Time of Day:** [flc:%s] %s".formatted(
            ecosystem.getCurrentTimeOfDay().name(),
            ecosystem.getCurrentTimeOfDay().getIcon()));
        System.out.println(str.toString());
    }

    public void displayEnvironmentConditions(EcosystemMan ecosystem) {
        this.displayTimeStatus(ecosystem);
        StringBuilder str = new StringBuilder();

        add.accept(str, "**🌍 [fly:Environment Status] 🌍**\n");

        // Get environment conditions

        add.accept(str, "  **Weather:** [flc:%s] %s".formatted(
            ecosystem.getCurrentWeather().name(),
            ecosystem.getCurrentWeather().getIcon()));

        add.accept(str, "  **Season:** [flc:%s] %s".formatted(
            ecosystem.getCurrentSeason().name(),
            ecosystem.getCurrentSeason().getIcon()));

        System.out.println(str.toString());
    }

    
}
