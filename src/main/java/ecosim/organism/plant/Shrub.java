package ecosim.organism.plant;

// import ecosim.weather.Weather;

public class Shrub extends DesertPlant {
    //private PlantSize size;

    public Shrub(PlantSize size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Shrub based on weather");
    }
}
