package ecosim.organism.plant;

import ecosim.enm.Size;
import ecosim.enm.Weather;



/*
 * This class represents a cactus in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */



public class Cactus extends DesertPlant {
    private static int cactusCount = 0;

    public Cactus() {
        super(Size.LARGE, ++cactusCount);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}


