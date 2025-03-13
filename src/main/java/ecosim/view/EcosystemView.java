package ecosim.view;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static ecosim.common.io.ConsoleIO.pprintln;
import static ecosim.common.io.ConsoleIO.prettify;
import static ecosim.common.io.ConsoleIO.toggleCursor;
import ecosim.enm.Biome;
import ecosim.man.EcosystemMan;
import ecosim.menu.AnimalMenu;
import ecosim.menu.BiomeMenu;
import ecosim.menu.OrganismMenu;
import ecosim.menu.PlantMenu;
import ecosim.organism.Organism;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;


public class EcosystemView {

    private static final BiConsumer<StringBuilder, String> add =
        (builder, str) -> builder.append(prettify(str)).append("\n");

    public void welcome() {
        SplashScreen.show();
        pprintln("Welcome to the *Ecosystem Simulator* 🌳");
        pprintln("To setup the ecosystem, please follow the prompts below.\n");
    }

    public void end(int exitCode) {
        toggleCursor(true);
        pprintln("\n[flr:(Simulator finished w/ exit code %d)]", exitCode);
    }

    public void displayDailyReport(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();

        add.accept(str, "**✨ [fly:Day %d Report] ✨**\n".formatted(ecosystem.getDayCount()));
        add.accept(str, "  **[flc:Headcount:]**");
        add.accept(str, "    Animals: **[flc:%d]**".formatted(ecosystem.getAnimalCount()));
        add.accept(str, "    Plants: **[flc:%d]**\n".formatted(ecosystem.getPlantCount()));
        add.accept(str, "  **[flc:Statistics:]**");

        add.accept(str, "    **Animals:**");
        addOrganismReport(ecosystem.getAnimals(), str);

        add.accept(str, "    **Plants:**");
        addOrganismReport(ecosystem.getPlants(), str);

        System.out.println(str.toString());
    }

    private <T extends Organism> void addOrganismReport(List<T> organisms, StringBuilder str) {
        int goodHealth = 70;
        int poorHealth = 30;

        add.accept(str, "      [flg:Thriving:]");
        organisms.stream()
            .filter(o -> o.getHealth() >= goodHealth)
            .forEach(o -> add.accept(str, "      • %s".formatted(o.getName())));

        add.accept(str, "      [flr:Declining:]\n");
        organisms.stream()
            .filter(o -> o.getHealth() <= poorHealth)
            .forEach(o -> add.accept(str, "      • %s".formatted(o.getName())));
    }

    public Biome promptBiomeSelection() {
        final BiomeMenu menu = new BiomeMenu(Biome.values());
        menu.print();

        final Biome choice = menu.getUserChoice();
        System.out.println();

        return choice;
    }

    public <T extends Organism> List<Class<? extends T>> promptOrganismSelection(OrganismMenu<T> menu, int num) {
        final List<Class<? extends T>> chosen = new ArrayList<>();
        menu.print();

        for (int i = 0; i < num; i++) {
            Class<? extends T> choice = menu.getUserChoice("Enter your choice (%d) >> ".formatted(i + 1));
            chosen.add(choice);
        }
        System.out.println();

        return chosen;
    }

    public List<Class<? extends Animal>> promptAnimalSelection(List<Class<? extends Animal>> animals, int num) {
        return promptOrganismSelection(new AnimalMenu(animals), num);
    }

    public List<Class<? extends Plant>> promptPlantSelection(List<Class<? extends Plant>> plants, int num) {
        return promptOrganismSelection(new PlantMenu(plants), num);
    }

    public void displayEnvironmentConditions(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();
        
        add.accept(str, "**🌍 [fly:Environment Status] 🌍**\n");
        add.accept(str, "  **Day:** [flc:%d]".formatted(ecosystem.getDayCount()));
        
        // Get environment conditions
        add.accept(str, "  **Time:** [flc:%s] %s".formatted(
            ecosystem.getCurrentTimeOfDay().name(),
            ecosystem.getCurrentTimeOfDay().getIcon()
        ));
        
        add.accept(str, "  **Weather:** [flc:%s] %s".formatted(
            ecosystem.getCurrentWeather().name(), 
            ecosystem.getCurrentWeather().getIcon()
        ));
        
        add.accept(str, "  **Season:** [flc:%s] %s".formatted(
            ecosystem.getCurrentSeason().name(),
            ecosystem.getCurrentSeason().getIcon()
        ));
        
        System.out.println(str.toString());
    }

}
