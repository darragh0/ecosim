package ecosim.organism.plant;

public class Cactus extends DesertPlant {
    
    private static int cactusCount = 0;

    public Cactus(PlantSize size, int x, int y) {
        super(size, x, y);
        this.name = "Cactus (" + ++cactusCount + ")";
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Cactus based on weather");
    }
}


