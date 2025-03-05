package ecosim.organism.plant;

// import ecosim.weather.Weather;

import ecosim.enm.Size;

public class Wildflower extends GrasslandPlant {
    
    private static int wildflowerCount = 0;

    public Wildflower(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Wildflower (" + ++wildflowerCount + ")";
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Wildflower based on weather");
    }
}
