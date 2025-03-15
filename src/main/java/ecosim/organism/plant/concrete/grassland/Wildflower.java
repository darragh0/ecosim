package ecosim.organism.plant.concrete.grassland;


import ecosim.enm.Weather;
import ecosim.organism.plant.abs.GrasslandPlant;


/**
 * This class represents a wildflower in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * 
 * @author MiaBorkoo
 */
public class Wildflower extends GrasslandPlant {

    private static int num = 0;

    public Wildflower() {
        super(++num);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }

}
