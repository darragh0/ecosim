package ecosim.organism.plant;

import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;
import ecosim.enm.Weather;

/*
 * This is the abstract class for all grassland plants.
 * It extends the Plant class and includes methods specific to grassland plants.
 * Author: @MiaBorkoo
 */

public abstract class GrasslandPlant extends Plant {

    public GrasslandPlant(PlantSize size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager, GrowthStrategy growthStrategy) {
        super(size, x, y, timeOfDayManager, weatherManager, growthStrategy);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        // Call the growth strategy to adjust the growth rate
        growthStrategy.adjustGrowthRate(this, currentWeather);
    }
}
