package ecosim.view;

import ecosim.EcosystemManager;
import ecosim.organism.Organism;

import java.util.List;

public class EcosystemView {

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
}
