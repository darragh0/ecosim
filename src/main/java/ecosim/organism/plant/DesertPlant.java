package ecosim.organism.plant;

/*
 * This is the abstract class for all desert plants.
 * It extends the Plant class and includes methods specific to desert plants.
 * Author: @MiaBorkoo
 */

import ecosim.enm.Size;

public abstract class DesertPlant extends Plant {
    public DesertPlant(Size size, int x, int y) {
        super(size, x, y);
    }
    
    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for DesertPlant based on weather");
        // TODO: update growth rate based on weather
    }
}
