package ecosim.organism.plant;


import ecosim.enm.Size;
import ecosim.enm.Weather;

/*
 * This is the abstract class for all desert plants.
 * It extends the Plant class and includes methods specific to desert plants.
 * Author: @MiaBorkoo
 */

public abstract class DesertPlant extends Plant {
   

    public DesertPlant(Size size, int num) {
        super(size, num);
    }
    
    @Override
    public void updateGrowthRate(Weather currentWeather) {
        float growthAdjustment = 0.0f;

        switch (currentWeather) {
            case SUNNY:
                growthAdjustment = 0.1f; // Increase growth rate by 10% if sunny
                break;
            case RAINY:
                growthAdjustment = 0.05f; // Increase growth rate by 5% if rainy
                break;
            case DRY:
                growthAdjustment = -0.2f; // Decrease growth rate by 20% if dry
                break;
            case CLOUDY:
                growthAdjustment = 0.02f; // Increase growth rate by 2% if cloudy
                break;
            case SNOWY:
                growthAdjustment = -0.3f; // Decrease growth rate by 30% if snowy
                break;
        }

        // Update the growth rate based on the adjustment
        this.growthRate += this.growthRate * growthAdjustment;
        System.out.println("Updated growth rate for " + this.getClass().getSimpleName() + ": " + this.growthRate);
    }
}
