package ecosim.organism.plant.concrete.grassland;


import ecosim.enm.Weather;
import ecosim.organism.plant.abs.GrasslandPlant;


/**
 * This class represents a bush in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * 
 * @author MiaBorkoo
 */
public class Bush extends GrasslandPlant {

    private static int num = 0;

    public Bush() {
        super(++num);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }

}
