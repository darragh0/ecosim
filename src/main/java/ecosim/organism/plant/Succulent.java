package ecosim.organism.plant;

// import ecosim.weather.Weather;

public class Succulent extends DesertPlant {
    public Succulent(PlantSize size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Succulent based on weather");
    }
}
