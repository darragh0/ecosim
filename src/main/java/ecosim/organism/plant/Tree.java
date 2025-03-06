package ecosim.organism.plant;

import ecosim.enm.Size;
import ecosim.enm.Weather;
import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;

/*
 * This class represents a tree in the ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Tree extends GrasslandPlant {
    public Tree(Size size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager) {
        super(size, x, y, timeOfDayManager, weatherManager);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}
