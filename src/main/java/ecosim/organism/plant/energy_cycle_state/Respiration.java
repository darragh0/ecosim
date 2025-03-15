package ecosim.organism.plant.energy_cycle_state;

import ecosim.enm.Weather;

// plants will respire at night -> night state
public class Respiration implements EnergyCycleState {
    @Override
    public float performEnergyCycle(float growthRate, Weather currentWeather) {
        float adjustedGrowthRate;
        float healthAdjustment;
        
        // Handle both growth and health adjustments
        switch (currentWeather) {
            case SUNNY -> {
                adjustedGrowthRate = growthRate * -0.05f; // Minor growth decrease even in good conditions
                healthAdjustment = -0.2f; // Minimal health decrease in good conditions
            }
            case DRY -> {
                adjustedGrowthRate = growthRate * -0.15f; // More significant growth decrease
                healthAdjustment = -1.0f; // More health decrease in dry conditions at night
            }
            case RAINY -> {
                adjustedGrowthRate = growthRate * -0.08f; // Moderate growth decrease
                healthAdjustment = -0.4f; // Moderate health decrease during rain at night
            }
            case SNOWY -> {
                adjustedGrowthRate = growthRate * -0.25f; // Significant growth decrease
                healthAdjustment = -1.5f; // Significant health decrease in snow at night
            }
            default -> {
                adjustedGrowthRate = growthRate * -0.1f; // Default growth decrease
                healthAdjustment = -0.5f; // Default health decrease during night
            }
        }
        
        growthRate += adjustedGrowthRate; // Update the growth rate

        System.out.println("Performing respiration. Growth rate decreased by: " + 
                          (-1 * adjustedGrowthRate) + ", Health adjustment: " + healthAdjustment);
        
        return healthAdjustment;
    }
}

