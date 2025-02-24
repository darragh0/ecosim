package ecosim.view;

import ecosim.BiomeManager;
import ecosim.EcosystemManager;
import ecosim.enm.Biome;
import ecosim.organism.Organism;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EcosystemView {

    private final Scanner scanner = new Scanner(System.in);

    public void displayDailyReport(EcosystemManager ecosystem) {
        StringBuilder report = new StringBuilder();
        report.append("📅 Day ").append(ecosystem.getDayCount()).append(" Report\n");
        report.append("=======================\n");
        report.append("Number of Organisms: \n");
        report.append("🌿 Plants: ").append(ecosystem.getPlantCount()).append("\n");
        report.append("🦁 Animals: ").append(ecosystem.getAnimalCount()).append("\n");
        report.append("=======================\n");
        report.append("Organisms Stats: \n");

        appendOrganismReport("Animals", ecosystem.getAnimals(), report);
        //appendOrganismReport("Plants", ecosystem.getPlants(), report);

        System.out.println(report.toString());
    }
    private <T extends Organism> void appendOrganismReport(String title, List<T> organisms, StringBuilder report) {
        int goodHealth = 70;
        int poorHealth = 30;

        report.append(title).append(":\n");
        report.append("   ⭐ Thriving:\n");
        organisms.stream()
                .filter(o -> o.getHealth() >= goodHealth)
                .forEach(o -> report.append("     • ").append(o.getName()).append("\n"));

        report.append("   ⚠ Declining:\n");
        organisms.stream()
                .filter(o -> o.getHealth() <= poorHealth)
                .forEach(o -> report.append("     • ").append(o.getName()).append("\n"));
    }

    public Biome promptBiomeSelection() {
        Biome[] biomes = Biome.values();
        Biome biomeSelection = null;


        while (biomeSelection == null) {
            System.out.println(this.biomeSelectionBuilder(biomes));

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty! Please enter a number.");
                continue;
            }

            try {
                int userChoice = Integer.parseInt(input);
                if (userChoice >= 1 && userChoice <= biomes.length) {
                    biomeSelection = biomes[userChoice - 1];
                } else {
                    System.out.println("Invalid option! Please enter a number between 1 and " + biomes.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        return biomeSelection;
    }

    private String biomeSelectionBuilder(Biome[] biomes) {
        StringBuilder biomeSelectionPrompt = new StringBuilder();
        biomeSelectionPrompt.append("==== Please Select a Biome ====\n");
        for (int i = 0; i < biomes.length; i++) {
            biomeSelectionPrompt.append("[").append(i + 1).append("] ")
                    .append(biomes[i].getName()).append("\n");
        }

        return biomeSelectionPrompt.toString();
    }

    private String organismSelectionBuilder(String organismType, List<String> organisms, int choicesLeft) {
        StringBuilder organismSelectionPrompt = new StringBuilder();
        organismSelectionPrompt.append("==== Please Choose an ").append(organismType).append(" ====\n");
        organismSelectionPrompt.append(" (").append(choicesLeft).append(") Choices Left\n");
        for (int i = 0; i < organisms.size(); i++) {
            organismSelectionPrompt.append("[").append(i + 1).append("] ")
                    .append(organisms.get(i)).append("\n");
        }
        return organismSelectionPrompt.toString();
    }

    public List<String> promptOrganismSelection(String organismType, List<String> organisms, int numberOfOrganisms) {
        ArrayList<String> chosenOrganisms = new ArrayList<>();

        for (int i = 0; i < numberOfOrganisms; i++) {
            String organismSelection = null;

            while (organismSelection == null) {
                System.out.println(this.organismSelectionBuilder(organismType, organisms, numberOfOrganisms - i));

                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty! Please enter a number.");
                    continue;
                }

                try {
                    int userChoice = Integer.parseInt(input);
                    if (userChoice >= 1 && userChoice <= organisms.size()) {
                        organismSelection = organisms.get(userChoice - 1);

                        // **Fix: Add selected organism to list**
                        chosenOrganisms.add(organismSelection);
                    } else {
                        System.out.println("Invalid option! Please enter a number between 1 and " + organisms.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }
        }

        return chosenOrganisms;
    }

}
