package ecosim.organism.plant;

// import ecosim.weather.Weather;

import ecosim.enm.Size;

public class Shrub extends DesertPlant {

    public Shrub(Size size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Shrub based on weather");
    }
}
