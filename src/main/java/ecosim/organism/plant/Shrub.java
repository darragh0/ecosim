package ecosim.organism.plant;
import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;
import ecosim.enm.Weather;

// import ecosim.weather.Weather;

/*
 * This class represents a shrub in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Shrub extends DesertPlant {
    //private PlantSize size;

    public Shrub(PlantSize size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager, GrowthStrategy growthStrategy) {
        super(size, x, y, timeOfDayManager, weatherManager, growthStrategy);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}
