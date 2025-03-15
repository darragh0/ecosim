package ecosim.organism.plant.concrete.grassland;


import ecosim.enm.Size;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.GrasslandPlant;


/*
 * This class represents a tree in the ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */



public class Tree extends GrasslandPlant {
    private static int treeCount = 0;

    public Tree() {
        super(Size.LARGE, ++treeCount);
        super.symbol = "🌳";

    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather); // Call the method from the base class
    }

}
