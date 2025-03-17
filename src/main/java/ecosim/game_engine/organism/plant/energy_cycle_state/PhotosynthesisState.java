package ecosim.game_engine.organism.plant.energy_cycle_state;

import ecosim.game_engine.enm.EnergyCycle;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.organism.plant.abs.Plant;

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
    public float performEnergyCycle(float growthRate) {
        // Increase daytime health gain significantly
        float baseIncrease = 1.2f;  // Increased from 0.45f
        float healthAdjustment = baseIncrease * (1.0f + (growthRate / 15f));  
        
        return healthAdjustment; // Now much more positive during day
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

    @Override
    public EnergyCycle getEnergyCycle() {
        return EnergyCycle.PHOTOSYNTHESIS;
    }
}