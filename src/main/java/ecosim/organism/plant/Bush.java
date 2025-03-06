package ecosim.organism.plant;

// import ecosim.weather.Weather;

import ecosim.enm.Size;


/*
 * This class represents a bush in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */
public class Bush extends GrasslandPlant {
    
    private static int bushCount = 0;

    public Bush(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Bush (" + ++bushCount + ")";
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Bush based on weather");
    }
}
