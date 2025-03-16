package ecosim.organism.plant.energy_cycle_state;

import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.Plant;

/**
 * Implementation of the plant energy cycle state during nighttime (respiration).
 * 
 * This class represents the energy consumption state of plants, where they:
 * - Consume stored energy for cellular maintenance
 * - Generally decrease in health as they use resources
 * - Experience reduced growth rates during all weather conditions
 * 
 * Respiration is a necessary process for plants, but results in net energy consumption
 * rather than production. The severity of health decrease varies by weather condition,
 * with harsh weather causing more significant health decreases. This state
 * transitions back to the photosynthesis state when day returns.
 * 
 * @author MiaBorkoo
 */
public class RespirationState implements EnergyCycleState {
    /**
     * Performs the respiration energy cycle, calculating health adjustments
     * and growth rate changes based on current weather conditions.
     * 
     * This method has two effects:
     * 1. It calculates and returns a health adjustment value (usually negative during night)
     * 2. It directly modifies the provided growthRate parameter based on weather
     * 
     * @param growthRate Current growth rate of the plant (modified by this method)
     * @param currentWeather Current weather affecting respiration
     * @return Amount to adjust plant health (usually negative during night)
     */
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

        return healthAdjustment;
    }

    /**
     * Handles time of day transitions, switching to photosynthesis state at day.
     * 
     * @param plant The plant experiencing the time change
     * @param timeOfDay The new time of day
     * @return This state if still night, or a new PhotosynthesisState if day
     */
    @Override
    public EnergyCycleState handleTimeOfDayChange(Plant plant, TimeOfDay timeOfDay) {
        if (timeOfDay == TimeOfDay.DAY) {
            return new PhotosynthesisState();
        }
        return this; // Stay in respiration state during night
    }
}