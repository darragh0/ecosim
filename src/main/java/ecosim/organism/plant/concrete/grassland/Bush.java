package ecosim.organism.plant.concrete.grassland;


import ecosim.enm.Size;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.GrasslandPlant;


/*
 * This class represents a bush in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */
public class Bush extends GrasslandPlant {
    private static int bushCount = 0;

    public Bush() {
        super(Size.MEDIUM, ++bushCount);
        super.symbol = "🌲";
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }

}
