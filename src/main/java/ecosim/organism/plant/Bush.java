package ecosim.organism.plant;

// import ecosim.weather.Weather;

public class Bush extends GrasslandPlant {
  

    public Bush(PlantSize size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Bush based on weather");
    }
}
