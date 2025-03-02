package ecosim.organism.plant;

import ecosim.enm.Weather;
import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;


// import ecosim.weather.Weather;

/*
 * This class represents a wildflower in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Wildflower extends GrasslandPlant {
    public Wildflower(PlantSize size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager, GrowthStrategy growthStrategy) {
        super(size, x, y, timeOfDayManager, weatherManager, growthStrategy);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        System.out.println("Updating growth rate for Wildflower based on weather");
    }
}
