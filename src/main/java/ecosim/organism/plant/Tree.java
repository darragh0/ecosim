package ecosim.organism.plant;

// import ecosim.weather.Weather;

public class Tree extends GrasslandPlant {
    
    private static int treeCount = 0;

    public Tree(PlantSize size, int x, int y) {
        super(size, x, y);
        this.name = "Tree (" + ++treeCount + ")";
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Tree based on weather");
    }
}
