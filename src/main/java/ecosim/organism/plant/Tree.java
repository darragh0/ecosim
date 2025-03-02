package ecosim.organism.plant;

// import ecosim.weather.Weather;

/*
 * This class represents a tree in the ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

public class Tree extends GrasslandPlant {
    

    public Tree(PlantSize size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Tree based on weather");
    }
}
