package ecosim.organism.plant;

import ecosim.enm.Size;



/*
 * This class represents a cactus in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */


import ecosim.enm.Weather;


public class Cactus extends DesertPlant {
    private static int cactusCount = 0;

    public Cactus(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Cactus (" + ++cactusCount + ")";
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}


