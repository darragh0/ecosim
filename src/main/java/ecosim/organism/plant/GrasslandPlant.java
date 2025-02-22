package ecosim.organism.plant;


public abstract class GrasslandPlant extends Plant {

    public GrasslandPlant(PlantSize size, int x, int y) {
        super(size, x, y);
           
    }
    
    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for DesertPlant based on weather");
        // TODO: update growth rate based on weather
        this.growthRate = this.growthRate + 0.1f; // example growth rate increase, can be modified
    }


}
