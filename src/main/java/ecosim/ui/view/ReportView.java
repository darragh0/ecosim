package ecosim.ui.view;


import static ecosim.common.io.ConsoleIO.add;
import static ecosim.common.io.ConsoleIO.strInput;

import java.util.List;
import java.util.stream.Collectors;

import ecosim.game_engine.man.EcosystemMan;
import ecosim.game_engine.organism.Organism;


public class ReportView {
    /**
     * Displays a daily report and prompts the user to press Enter to continue.
     * 
     * @param ecosystem The ecosystem manager containing the current state data
     */
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

        addLifeCycleReport("Animals", ecosystem.getNewbornAnimals(), ecosystem.getDeadAnimals(), str, maxWidth);

        addLifeCycleReport("Plants", ecosystem.getNewbornPlants(), ecosystem.getDeadPlants(), str, maxWidth);

        // Display the report
        System.out.println(str.toString());

        // Prompt user to press Enter to continue to next day
        if (ecosystem.getDayCount() < ecosystem.getMaxDays())
            strInput("Press **[flr:Enter]** to continue to the next day >> ", true);
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
        final double goodHealthPercentage = 0.6; // 70% of maximum health
        final double poorHealthPercentage = 0.4; // 30% of maximum health

        final List<T> thriving = organisms.stream()
            .filter(o -> {
                double healthPercentage = (double) o.getHealth() / o.getMaxHealth();
                return healthPercentage >= goodHealthPercentage;
            })
            .collect(Collectors.toList());

        final List<T> declining = organisms.stream()
            .filter(o -> {
                double healthPercentage = (double) o.getHealth() / o.getMaxHealth();
                return healthPercentage < poorHealthPercentage;
            })
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

    private <T extends Organism> void addLifeCycleReport(String type, List<T> newborns, List<T> deceased,
        StringBuilder str, int maxWidth) {
        final int gap = 10;

        // Column headers
        final String newbornHeader = "New %s:".formatted(type);
        final String deceasedHeader = "Deceased %s:".formatted(type);

        int headerPadding = maxWidth - newbornHeader.length() + gap;
        final String headerStr =
            "    [flg:%s]%s[flr:%s]".formatted(newbornHeader, " ".repeat(headerPadding), deceasedHeader);
        add.accept(str, headerStr);

        final int maxRows = Math.max(newborns.size(), deceased.size());
        if (maxRows == 0) {
            add.accept(str, " ".repeat(maxWidth - 1));
        } else {

            for (int i = 0; i < maxRows; i++) {
                StringBuilder row = new StringBuilder();

                // Left column (newborns)
                if (i < newborns.size()) {
                    T organism = newborns.get(i);
                    String rowStr = String.format("      > %s ", organism.getName());
                    row.append(rowStr);

                    int padding = maxWidth - rowStr.length() + gap;
                    if (padding > 0)
                        row.append(" ".repeat(padding));
                } else {
                    row.append(" ".repeat(maxWidth + gap));
                }

                // Right column (deceased)
                if (i < deceased.size()) {
                    T organism = deceased.get(i);
                    row.append(String.format("      > %s", organism.getName()));
                }

                add.accept(str, row.toString());
            }
        }

        add.accept(str, "");
    }

    /**
    * Displays a comprehensive final report when the ecosystem simulation ends.
    * 
    * @param ecosystem The ecosystem manager containing all simulation data
    * @param totalNewAnimals Total count of animals born throughout the simulation
    * @param totalDeadAnimals Total count of animals that died throughout the simulation
    * @param totalDeadPlants Total count of plants that died throughout the simulation
    */
    public void displayFinalReport(EcosystemMan ecosystem) {
        StringBuilder str = new StringBuilder();

        // Calculate seasons (each season lasts 5 days)
        int totalSeasons = (int) Math.ceil(ecosystem.getDayCount() / 5.0);

        add.accept(str, "\n===========================================================");
        add.accept(str, "**✨ [fly:ECOSYSTEM SIMULATION FINAL REPORT] ✨**");
        add.accept(str, "===========================================================\n");

        // Time statistics
        add.accept(str, "**[flc:TIME STATISTICS]**");
        add.accept(str, "  Total Days Simulated:  **[flg:%d]**".formatted(ecosystem.getDayCount()));
        add.accept(str, "  Seasons Experienced:   **[flg:%d]**".formatted(totalSeasons));
        add.accept(str, "  Final Season:          **[flg:%s]**".formatted(ecosystem.getCurrentSeason()));
        add.accept(str, "");

        // Population statistics
        add.accept(str, "**[flc:POPULATION STATISTICS]**");
        add.accept(str, "  Final Animal Count:    **[flg:%d]**".formatted(ecosystem.getAnimalCount()));
        add.accept(str, "  Final Plant Count:     **[flg:%d]**".formatted(ecosystem.getPlantCount()));
        add.accept(str, "");

        // Lifecycle statistics
        add.accept(str, "**[flc:LIFECYCLE STATISTICS]**");
        add.accept(str, "  New Animals Born:      **[flg:%d]**".formatted(ecosystem.getTotalNewbornAnimals()));
        add.accept(str, "  New Plants Born:       **[flg:%d]**".formatted(ecosystem.getTotalNewbornPlants()));
        add.accept(str, "  Animals Deceased:      **[flr:%d]**".formatted(ecosystem.getTotalDeadAnimals()));
        add.accept(str, "  Plants Deceased:       **[flr:%d]**".formatted(ecosystem.getTotalDeadPlants()));
        add.accept(str, "");


        add.accept(str, "**[flc:ECOSYSTEM ANALYSIS]**");
        // Final ecosystem state
        if (ecosystem.isAtMaxCapacity()) {
            add.accept(str, "  Ecosystem Status:      **[flr:Overpopulated!]**");
        } else if (!ecosystem.isEcosystemAlive()) {
            add.accept(str, "  Ecosystem Status:      **[flr:Collapsed!]**");
        } else {
            add.accept(str, "  Ecosystem Status:      **[flg:Survived!]**");
        }


        add.accept(str, "\n===========================================================");
        add.accept(str, "**[fly:THANK YOU FOR PLAYING ECOSYSTEM SIMULATOR!]**");
        add.accept(str, "===========================================================");

        System.out.println(str.toString());
    }



}
