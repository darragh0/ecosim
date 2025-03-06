package ecosim.organism.plant;

// import ecosim.weather.Weather;


import ecosim.enm.Size;

/*
 * This class represents a tree in the ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */



public class Tree extends GrasslandPlant {
    
    private static int treeCount = 0;

    public Tree(Size size, int x, int y) {
        super(size, x, y);
        this.name = "Tree (" + ++treeCount + ")";
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Tree based on weather");
    }
}
