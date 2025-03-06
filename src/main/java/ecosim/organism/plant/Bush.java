package ecosim.organism.plant;

import ecosim.enm.Size;
import ecosim.enm.Weather;


/*
 * This class represents a bush in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Bush extends GrasslandPlant {
    public Bush(Size size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}
