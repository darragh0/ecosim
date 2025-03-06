package ecosim.organism.plant;

import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;
import ecosim.enm.Size;
import ecosim.enm.Weather;

/*
 * This is the abstract class for all grassland plants.
 * It extends the Plant class and includes methods specific to grassland plants.
 * Author: @MiaBorkoo
 */

public abstract class GrasslandPlant extends Plant {

    public GrasslandPlant(Size size, int x, int y, TimeOfDayManager timeOfDayManager, WeatherManager weatherManager) {
        super(size, x, y, timeOfDayManager, weatherManager);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        float growthAdjustment = 0.0f;

        switch (currentWeather) {
            case SUNNY:
                growthAdjustment = 0.2f; // Increase growth rate by 20% if sunny
                break;
            case RAINY:
                growthAdjustment = 0.15f; // Increase growth rate by 15% if rainy
                break;
            case DRY:
                growthAdjustment = -0.1f; // Decrease growth rate by 10% if dry
                break;
            case CLOUDY:
                growthAdjustment = 0.05f; // Increase growth rate by 5% if cloudy
                break;
            case SNOWY:
                growthAdjustment = -0.2f; // Decrease growth rate by 20% if snowy
                break;
        }

        // Update the growth rate based on the adjustment
        this.growthRate += this.growthRate * growthAdjustment;
        System.out.println("Updated growth rate for " + this.getClass().getSimpleName() + ": " + this.growthRate);
    }
}
