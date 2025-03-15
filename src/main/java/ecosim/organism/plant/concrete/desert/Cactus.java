package ecosim.organism.plant.concrete.desert;


import ecosim.enm.Weather;
import ecosim.organism.plant.abs.DesertPlant;



/*
 * This class represents a cactus in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * 
 * @author MiaBorkoo
 */
public class Cactus extends DesertPlant {

    private static int num = 0;

    public Cactus() {
        super(++num);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }

}


