package ecosim.organism.plant.concrete.grassland;


import ecosim.enm.Weather;
import ecosim.organism.plant.abs.GrasslandPlant;


/*
 * This class represents a tree in the ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * 
 * @author MiaBorkoo
 */
public class Tree extends GrasslandPlant {

    private static int num = 0;

    public Tree() {
        super(++num);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather); // Call the method from the base class
    }

    @Override
    public Tree clone() {
        return new Tree();
    }

}
