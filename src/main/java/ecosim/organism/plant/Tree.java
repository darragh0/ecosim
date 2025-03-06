package ecosim.organism.plant;

import ecosim.enm.Size;
import ecosim.enm.Weather;


/*
 * This class represents a tree in the ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Tree extends GrasslandPlant {
    public Tree(Size size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather); // Call the method from the base class
    }
}
