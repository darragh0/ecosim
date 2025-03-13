package ecosim.view;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static ecosim.common.io.ConsoleIO.prettify;
import static ecosim.common.io.ConsoleIO.prettyPrintln;
import static ecosim.common.io.ConsoleIO.toggleCursor;
import ecosim.enm.Biome;
import ecosim.man.EcosystemMan;
import ecosim.menu.AnimalMenu;
import ecosim.menu.BiomeMenu;
import ecosim.menu.OrganismMenu;
import ecosim.menu.PlantMenu;
import ecosim.organism.Organism;
import ecosim.organism.animal.Animal;
import ecosim.organism.plant.Plant;


public class EcosystemView {

    private static final BiConsumer<StringBuilder, String> add =
        (builder, str) -> builder.append(prettify(str)).append("\n");

    public void welcome() {
        SplashScreen.show();
        prettyPrintln("<B><g>[Simulation Started]</g></B>\n");
        prettyPrintln("Welcome to the Ecosystem Simulator!");
        prettyPrintln("To setup the ecosystem, please follow the prompts below.\n");
    }

    public void end(int exitCode) {
        toggleCursor(true);
        prettyPrintln("\n<B><r>[Simulator finished w/ exit code %d]</r></B>", exitCode);
    }

    public void displayDailyReport(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();

        add.accept(str, "<B>✨ <y>Day %d Report</y> ✨</B>\n".formatted(ecosystem.getDayCount()));
        add.accept(str, "  <B><c>Headcount:</c></B>");
        add.accept(str, "    Animals: <B><c>%d</c></B>".formatted(ecosystem.getAnimalCount()));
        add.accept(str, "    Plants: <B><c>%d</c></B>\n".formatted(ecosystem.getPlantCount()));
        add.accept(str, "  <B><c>Statistics:</c></B>");

        add.accept(str, "    <B>Animals:</B>");
        addOrganismReport(ecosystem.getAnimals(), str);

        add.accept(str, "    <B>Plants:</B>");
        addOrganismReport(ecosystem.getPlants(), str);

        System.out.println(str.toString());
    }

    private <T extends Organism> void addOrganismReport(List<T> organisms, StringBuilder str) {
        int goodHealth = 70;
        int poorHealth = 30;

        add.accept(str, "      <g>Thriving:</g>");
        organisms.stream()
            .filter(o -> o.getHealth() >= goodHealth)
            .forEach(o -> add.accept(str, "      • %s".formatted(o.getName())));

        add.accept(str, "      <r>Declining:</r>\n");
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

}
