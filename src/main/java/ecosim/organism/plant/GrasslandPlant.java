package ecosim.organism.plant;

/*
 * This is the abstract class for all grassland plants.
 * It extends the Plant class and includes methods specific to grassland plants.
 * Author: @MiaBorkoo
 */

import ecosim.enm.Size;

public abstract class GrasslandPlant extends Plant {

    public GrasslandPlant(Size size, int x, int y) {
        super(size, x, y);
           
    }
    
    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for DesertPlant based on weather");
        // TODO: update growth rate based on weather
        this.growthRate = this.growthRate + 0.1f; // example growth rate increase, can be modified
    }


}
