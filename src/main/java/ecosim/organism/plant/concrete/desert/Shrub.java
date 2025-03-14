package ecosim.organism.plant.concrete.desert;


import ecosim.enm.Size;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.DesertPlant;

// import ecosim.weather.Weather;



/*
 * This class represents a shrub in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

// private PlantSize size;


public class Shrub extends DesertPlant {
    // private PlantSize size;
    private static int shrubCount = 0;


    public Shrub() {
        super(Size.SMALL, ++shrubCount);
        super.symbol = "🌿";
    }



    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }

}
