package ecosim.organism.plant;

import ecosim.enm.Size;
import ecosim.enm.Weather;
/*
 * This class represents a wildflower in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */
public class Wildflower extends GrasslandPlant {
    private static int wildflowerCount = 0;

    public Wildflower() {
        super(Size.SMALL, "Wildflower (" + ++wildflowerCount + ")");
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather); 
    }
}
