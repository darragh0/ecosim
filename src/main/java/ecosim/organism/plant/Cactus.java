package ecosim.organism.plant;

/*
 * This class represents a cactus in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;
import ecosim.enm.Weather;
import ecosim.enm.Size;

public class Cactus extends DesertPlant {
    public Cactus(Size size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager) {
        super(size, x, y, timeOfDayManager, weatherManager);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}


