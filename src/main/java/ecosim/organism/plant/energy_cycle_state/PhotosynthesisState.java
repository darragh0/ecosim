package ecosim.organism.plant.energy_cycle_state;

import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.Plant;

/**
 * Implementation of the plant energy cycle state during daytime (photosynthesis).
 * 
 * This class represents the active energy production state of plants, where they:
 * - Convert sunlight, water and CO2 into energy
 * - Generally increase in health during favorable conditions
 * - Adjust growth rates based on weather conditions
 * 
 * Photosynthesis is most effective in sunny conditions, but still functions
 * in other weather types with varying degrees of efficiency. This state
 * transitions to the respiration state when night falls.
 * 
 * @author MiaBorkoo
 */
public class PhotosynthesisState implements EnergyCycleState {
    /**
     * Performs the photosynthesis energy cycle, calculating health adjustments
     * and growth rate changes based on current weather conditions.
     * 
     * This method has two effects:
     * 1. It calculates and returns a health adjustment value (usually positive during day)
     * 2. It directly modifies the provided growthRate parameter based on weather
     * 
     * @param growthRate Current growth rate of the plant (modified by this method)
     * @param currentWeather Current weather affecting photosynthesis
     * @return Amount to adjust plant health (usually positive during day)
     */
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
        
        return healthAdjustment;
    }

    /**
     * Handles time of day transitions, switching to respiration state at night.
     * 
     * @param plant The plant experiencing the time change
     * @param timeOfDay The new time of day
     * @return This state if still day, or a new RespirationState if night
     */
    @Override
    public EnergyCycleState handleTimeOfDayChange(Plant plant, TimeOfDay timeOfDay) {
        if (timeOfDay == TimeOfDay.NIGHT) {
            return new RespirationState();
        }
        return this; // Stay in photosynthesis state during day
    }
}