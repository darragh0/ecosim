package ecosim.organism.plant;

// import ecosim.weather.Weather;

import ecosim.enm.Size;


/*
 * This class represents a shrub in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Shrub extends DesertPlant {
    //private PlantSize size;
=======
import ecosim.enm.Size;
>>>>>>> f9709aa49f0ddb64351c8df935bf336063591e99

public class Shrub extends DesertPlant {

    public Shrub(Size size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Shrub based on weather");
    }
}
