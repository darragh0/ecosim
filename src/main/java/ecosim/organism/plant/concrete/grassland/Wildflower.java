package ecosim.organism.plant.concrete.grassland;


import ecosim.enm.Size;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.GrasslandPlant;


/*
 * This class represents a wildflower in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */
public class Wildflower extends GrasslandPlant {
    private static int wildflowerCount = 0;

    public Wildflower() {
        super(Size.SMALL, ++wildflowerCount);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }

}
