package ecosim;
import ecosim.organism.Organism;
import ecosim.organism.animal.Animal;
import ecosim.organism.plant.Plant;

import java.util.ArrayList;

public class EcosystemManager {
    private final Environment environment;
    private int dayCount;
    private ArrayList<Animal> animals;
    private ArrayList<Plant> plants;

    public EcosystemManager() {
        this.environment = new Environment();
        this.dayCount = 1;
        this.animals = new ArrayList<Animal>();
        this.plants = new ArrayList<Plant>();
    }

    public void setup(){
        // TODO: implement the environment and biome setup
    }

    public void dailyUpdate(){
        // TODO: implement creating the loop for daily simulation
    }

    public String generateDailyReport(){
        StringBuilder report = new StringBuilder();
        report.append("📅 Day ").append(dayCount).append(" Report\n");
        report.append("=======================\n");
        report.append("Number of Organisms: \n");
        report.append("🌿 Plants: ").append(plants.size()).append("\n");
        report.append("🦁 Animals: ").append(animals.size()).append("\n");
        report.append("=======================\n");
        report.append("Organisms Stats: \n");
        //appendOrganismReport("Plants", plants, report);
        appendOrganismReport("Animals", animals, report);

        return report.toString();
    }

    private <T extends Organism> void appendOrganismReport(String title, ArrayList<T> organisms, StringBuilder report) {
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

    public void createAnimal(){
        // TODO: implement creating an animal based off of the factory
    }

    public void createPlant(){
        // TODO: implement creating a plant based off of the factory
    }

    public void populateMap(){
        // TODO: implement populating the map and environment with plants and animals

    }



    
}
