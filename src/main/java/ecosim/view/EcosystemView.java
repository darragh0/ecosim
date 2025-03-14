package ecosim.view;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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
        add.accept(str, "    Plants:  **[flc:%d]**\n".formatted(ecosystem.getPlantCount()));
        add.accept(str, "  **[flc:Statistics:]**");

        int maxWidth = calculateMaxWidth(ecosystem);
        addOrganismReport("Animals", ecosystem.getAnimals(), str, maxWidth);
        addOrganismReport("Plants", ecosystem.getPlants(), str, maxWidth);

        System.out.println(str.toString());
    }

    private int calculateMaxWidth(EcosystemMan ecosystem) {
        int animalMaxLength = ecosystem.getAnimals().stream()
            .mapToInt(o -> o.getName().length())
            .max()
            .orElse(0);

        int plantMaxLength = ecosystem.getPlants().stream()
            .mapToInt(o -> o.getName().length())
            .max()
            .orElse(0);

        return Math.max(Math.max(animalMaxLength, plantMaxLength), "Thriving Animals:".length());
    }

    private <T extends Organism> void addOrganismReport(final String type, List<T> organisms, StringBuilder str,
        int maxWidth) {
        final int gap = 10;
        final int goodHealth = 70;
        final int poorHealth = 30;

        final List<T> thriving = organisms.stream()
            .filter(o -> o.getHealth() >= goodHealth)
            .collect(Collectors.toList());

        final List<T> declining = organisms.stream()
            .filter(o -> o.getHealth() <= poorHealth)
            .collect(Collectors.toList());

        final String thrivingHeader = "Thriving %s:".formatted(type);
        final String decliningHeader = "Declining %s:".formatted(type);

        int headerPadding = maxWidth - thrivingHeader.length() + gap;
        final String fStr =
            "    [flg:%s]%s[flr:%s]".formatted(thrivingHeader, " ".repeat(headerPadding), decliningHeader);
        add.accept(str, fStr);

        final int maxRows = Math.max(thriving.size(), declining.size());

        for (int i = 0; i < maxRows; i++) {
            StringBuilder row = new StringBuilder();

            if (i < thriving.size()) {
                final String name = thriving.get(i).getName();
                final String rowStr = String.format("      > %s", name);
                row.append(rowStr);

                int padding = maxWidth - rowStr.length() + gap;
                if (padding > 0)
                    row.append(" ".repeat(padding));
            } else {
                row.append(" ".repeat(maxWidth + gap));
            }

            if (i < declining.size())
                row.append("      > %s".formatted(declining.get(i).getName()));

            add.accept(str, row.toString());
        }
        add.accept(str, "");
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

    public void displayTimeStatus(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();
        
        add.accept(str, "**⏱️ [fly:Simulation Time] ⏱️**\n");
        add.accept(str, "  **Day:** [flc:%d]".formatted(ecosystem.getDayCount()));
        add.accept(str, "  **Time of Day:** [flc:%s] %s".formatted(
            ecosystem.getCurrentTimeOfDay().name(),
            ecosystem.getCurrentTimeOfDay().getIcon()
        ));
        System.out.println(str.toString());
    }
    
    public void displayEnvironmentConditions(EcosystemMan ecosystem) {
        this.displayTimeStatus(ecosystem);
        StringBuilder str = new StringBuilder();
        
        add.accept(str, "**🌍 [fly:Environment Status] 🌍**\n");
        
        // Get environment conditions
        
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
