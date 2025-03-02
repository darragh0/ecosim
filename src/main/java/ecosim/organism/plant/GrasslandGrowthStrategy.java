package ecosim.organism.plant;

import ecosim.enm.Weather;

public class GrasslandGrowthStrategy implements GrowthStrategy {
    @Override
    public void adjustGrowthRate(Plant plant, Weather currentWeather) {
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
        plant.growthRate += plant.growthRate * growthAdjustment;
        System.out.println("Updated growth rate for " + plant.getClass().getSimpleName() + ": " + plant.growthRate);
    }
} 