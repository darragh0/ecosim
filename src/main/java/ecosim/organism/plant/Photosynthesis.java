package ecosim.organism.plant;


public class Photosynthesis implements EnergyCycleState {
    @Override
    public void performEnergyCycle(float growthRate) {
        System.out.println("Performing photosynthesis. Growth rate increased by: " + growthRate); // do plants grow?
    }

}
