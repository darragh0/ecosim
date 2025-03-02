package ecosim.organism.plant;

/*
 * This is the abstract class for all desert plants.
 * It extends the Plant class and includes methods specific to desert plants.
 * Author: @MiaBorkoo
 */

public abstract class DesertPlant extends Plant {
    public DesertPlant(PlantSize size, int x, int y) {
        super(size, x, y);
    }
    
    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for DesertPlant based on weather");
        // TODO: update growth rate based on weather
    }
}
