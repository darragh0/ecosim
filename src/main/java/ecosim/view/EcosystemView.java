package ecosim.view;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import ecosim.EcosystemManager;
import ecosim.enm.Biome;
import ecosim.menu.AnimalMenu;
import ecosim.menu.BiomeMenu;
import ecosim.menu.OrganismMenu;
import ecosim.menu.PlantMenu;
import ecosim.organism.Organism;
import static ecosim.util.io.ConsoleIO.prettify;


public class EcosystemView {

    private static final BiConsumer<StringBuilder, String> add =
        (builder, str) -> builder.append(prettify(str)).append("\n");

    public void displayDailyReport(EcosystemManager ecosystem) {
        StringBuilder str = new StringBuilder();

        add.accept(str, "<B>✨ <y>Day %d Report</y> ✨</B>\n".formatted(ecosystem.getDayCount()));
        add.accept(str, "  <B><c>Headcount:</c></B>");
        add.accept(str, "    Animals: <B><c>%d</c></B>".formatted(ecosystem.getAnimalCount()));
        add.accept(str, "    Plants: <B><c>%d</c></B>\n".formatted(ecosystem.getPlantCount()));
        add.accept(str, "  <B><c>Statistics:</c></B>");

        add.accept(str, "    <B>Animals:</B>");
        addOrganismReport(ecosystem.getAnimals(), str);

        add.accept(str, "    <B>Plants:</B>");
        // appendOrganismReport(ecosystem.getPlants(), report);

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

    public List<String> promptOrganismSelection(OrganismMenu menu, int num) {
        final List<String> chosen = new ArrayList<>();
        menu.print();

        for (int i = 0; i < num; i++) {
            String choice = menu.getUserChoice("Enter your choice (%d) >> ".formatted(i + 1));
            chosen.add(choice);
        }
        System.out.println();

        return chosen;
    }

    public List<String> promptAnimalSelection(List<String> animals, int num) {
        return promptOrganismSelection(new AnimalMenu(animals), num);
    }

    public List<String> promptPlantSelection(List<String> plants, int num) {
        return promptOrganismSelection(new PlantMenu(plants), num);
    }

}
