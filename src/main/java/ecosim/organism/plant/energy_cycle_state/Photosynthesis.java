package ecosim.organism.plant.energy_cycle_state;


// Plants will photosynthesize at day -> day state
import ecosim.enm.Weather;


public class Photosynthesis implements EnergyCycleState {
    @Override
    public float performEnergyCycle(float growthRate, Weather currentWeather) {
        float adjustedGrowthRate;
        float healthAdjustment;

        // Both growth rate and health adjustments
        switch (currentWeather) {
            case SUNNY -> {
                adjustedGrowthRate = growthRate * 1.15f;
                healthAdjustment = 2.0f; // Good health increase during sunny conditions
            }
            case DRY -> {
                adjustedGrowthRate = growthRate * 1.07f;
                healthAdjustment = 0.5f; // Smaller health increase during dry conditions
            }
            case RAINY -> {
                adjustedGrowthRate = growthRate * 1.1f;
                healthAdjustment = 1.5f; // Good health increase during rain
            }
            case SNOWY -> {
                adjustedGrowthRate = growthRate * 0.8f;
                healthAdjustment = -0.5f; // Small health decrease during snow
            }
            default -> {
                adjustedGrowthRate = growthRate * 1.02f;
                healthAdjustment = 0.2f; // Small health increase for other conditions
            }
        }

        growthRate += adjustedGrowthRate; // Update the growth rate
        
        System.out.println("Performing photosynthesis. Growth rate increased by: " + 
                          adjustedGrowthRate + ", Health adjustment: " + healthAdjustment);
        
        return healthAdjustment;
    }

}
