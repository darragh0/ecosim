package ecosim.organism.plant;

import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;
import ecosim.enm.Weather;

/*
 * This class represents a tree in the ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Tree extends GrasslandPlant {
    

    public Tree(PlantSize size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager, GrowthStrategy growthStrategy) {
        super(size, x, y, timeOfDayManager, weatherManager, growthStrategy);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather); // Call the method from the base class
    }
}
