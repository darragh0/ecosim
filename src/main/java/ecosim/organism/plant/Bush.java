package ecosim.organism.plant;

// import ecosim.weather.Weather;

public class Bush extends GrasslandPlant {
    
    private static int bushCount = 0;

    public Bush(PlantSize size, int x, int y) {
        super(size, x, y);
        this.name = "Bush (" + ++bushCount + ")";
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Bush based on weather");
    }
}
