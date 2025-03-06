package ecosim.organism.plant;

import ecosim.enm.Weather;
import ecosim.enm.Size;

// import ecosim.weather.Weather;

/*
 * This class represents a shrub in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Shrub extends DesertPlant {
    //private PlantSize size;
    private static int shrubCount = 0;


    public Shrub(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Shrub (" + ++shrubCount + ")";
    }

    

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}
