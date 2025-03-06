package ecosim.organism.plant;

import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;
import ecosim.enm.Weather;
import ecosim.enm.Size;

/*
 * This class represents a succulent in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

// import ecosim.weather.Weather;

public class Succulent extends DesertPlant {
    public Succulent(Size size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager) {
        super(size, x, y, timeOfDayManager, weatherManager);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}
