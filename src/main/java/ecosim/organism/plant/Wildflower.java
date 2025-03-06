package ecosim.organism.plant;

import ecosim.enm.Weather;


import ecosim.enm.Size;

// import ecosim.weather.Weather;

import ecosim.enm.Size;

import ecosim.enm.Size;
/*
 * This class represents a wildflower in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */
public class Wildflower extends GrasslandPlant {
    private static int wildflowerCount = 0;

    public Wildflower(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Wildflower (" + ++wildflowerCount + ")";
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather); 
    }
}
