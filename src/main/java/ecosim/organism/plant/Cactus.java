package ecosim.organism.plant;

import ecosim.enm.Size;

public class Cactus extends DesertPlant {
    public Cactus(Size size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Cactus based on weather");
    }
}


