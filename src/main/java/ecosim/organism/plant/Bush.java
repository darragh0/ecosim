package ecosim.organism.plant;

// import ecosim.weather.Weather;

/*
 * This class represents a bush in the grassland ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Bush extends GrasslandPlant {
  

    public Bush(PlantSize size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Bush based on weather");
    }
}
