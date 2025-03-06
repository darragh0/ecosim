package ecosim.organism.plant;

// import ecosim.weather.Weather;

import ecosim.enm.Size;


/*
 * This class represents a shrub in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

    //private PlantSize size;


public class Shrub extends DesertPlant {
    
    private static int shrubCount = 0;

    public Shrub(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Shrub (" + ++shrubCount + ")";
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Shrub based on weather");
    }
}
