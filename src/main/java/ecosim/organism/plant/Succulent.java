package ecosim.organism.plant;

// import ecosim.weather.Weather;

import ecosim.enm.Size;

public class Succulent extends DesertPlant {

    private static int succulentCount = 0;

    public Succulent(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Succulent (" + ++succulentCount + ")";
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Succulent based on weather");
    }
}
