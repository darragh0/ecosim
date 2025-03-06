package ecosim.organism.plant;

import ecosim.enm.Weather;


import ecosim.enm.Size;

// import ecosim.weather.Weather;

/*
 * This class represents a wildflower in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Wildflower extends GrasslandPlant {
    public Wildflower(Size size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        System.out.println("Updating growth rate for Wildflower based on weather");
    }
}
