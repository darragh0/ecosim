package ecosim.organism.plant;


import ecosim.enm.Weather;
import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;

/*
 * This class represents a bush in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Bush extends GrasslandPlant {
  

    public Bush(PlantSize size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager, GrowthStrategy growthStrategy) {
        super(size, x, y, timeOfDayManager, weatherManager, growthStrategy);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        System.out.println("Updating growth rate for Bush based on weather");
    }
}
