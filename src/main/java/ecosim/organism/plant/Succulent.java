package ecosim.organism.plant;


import ecosim.enm.Weather;
import ecosim.enm.Size;

/*
 * This class represents a succulent in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

// import ecosim.weather.Weather;

import ecosim.enm.Size;

public class Succulent extends DesertPlant {
    private static int succulentCount = 0;
    
    public Succulent(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Succulent (" + ++succulentCount + ")";
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}
