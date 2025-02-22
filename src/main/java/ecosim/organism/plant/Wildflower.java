package ecosim.organism.plant;

// import ecosim.weather.Weather;

public class Wildflower extends GrasslandPlant {
    public Wildflower(PlantSize size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Wildflower based on weather");
    }
}
