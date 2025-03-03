package ecosim.organism.plant;

// import ecosim.weather.Weather;


import ecosim.enm.Size;

/*
 * This class represents a tree in the ecosystem.
 * It extends the GrasslandPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */
=======
import ecosim.enm.Size;
>>>>>>> f9709aa49f0ddb64351c8df935bf336063591e99

public class Tree extends GrasslandPlant {
    

    public Tree(Size size, int x, int y) {
        super(size, x, y);
    }

    @Override
    public void updateGrowthRate(/* Weather weather */) {
        System.out.println("Updating growth rate for Tree based on weather");
    }
}
