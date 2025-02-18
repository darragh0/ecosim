package ecosim.organism.plant;


/*
 * This is the class for the DesertPlant.
 * It extends the Plant class - own method for growth rate
 * Author: @MiaBorkoo
 */

public class DesertPlant extends Plant {
    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for GrasslandPlant based on weather");
    }



}
