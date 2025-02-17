package ecosim.plants;

public class Respiration implements EnergyCycleState {
    @Override
    public void performEnergyCycle(float growthRate) {
        System.out.println("Performing respiration. Growth rate decreased by: " + growthRate); //do plants shrink?
    }
} 