package ecosim.organism.plant;

import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;
import ecosim.enm.Weather;

/*
 * This is the abstract class for all desert plants.
 * It extends the Plant class and includes methods specific to desert plants.
 * Author: @MiaBorkoo
 */

public abstract class DesertPlant extends Plant {
    public DesertPlant(PlantSize size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager, GrowthStrategy growthStrategy) {
        super(size, x, y, timeOfDayManager, weatherManager, growthStrategy);
    }
    
    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}
