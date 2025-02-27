package ecosim.organism.plant;

// import ecosim.weather.Weather;

public class Tree extends GrasslandPlant {
    

    public Tree(PlantSize size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Tree based on weather");
    }
}
