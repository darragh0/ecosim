package ecosim.organism.plant;


import ecosim.enm.Size;
import ecosim.enm.Weather;

/*
 * This class represents a succulent in the desert ecosystem.
 * It extends the DesertPlant class and implements growth rate updates.
 * Author: @MiaBorkoo
 */

// import ecosim.weather.Weather;

public class Succulent extends DesertPlant {
    private static int succulentCount = 0;
    
    public Succulent() {
        super(Size.SMALL);
        this.name = "Succulent (" + ++succulentCount + ")";
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        super.updateGrowthRate(currentWeather);
    }
}
