package ecosim.organism.plant.energy_cycle_state;
import ecosim.enm.TimeOfDay;
import ecosim.organism.plant.abs.Plant;

/**
 * Interface representing a plant's energy cycle state.
 * 
 * This interface is part of the State pattern implementation for plant energy cycles.
 * Plants alternate between different energy states (photosynthesis/respiration)
 * based on time of day. Each state affects plant health and growth differently.
 * 
 * The energy cycle state:
 * - Determines how the plant generates/consumes energy
 * - Adjusts plant health based on current conditions
 * - Modifies growth rate based on environmental factors
 * - Handles transitions between different energy states
 * 
 * @author MiaBorkoo
 */
public interface EnergyCycleState {
    /**
     * Performs the energy cycle calculation with dual effects on health and growth.
     * This method:
     * 1. Calculates health adjustment based on weather and plant state
     * 2. Modifies the plant's growth rate (applied to the passed parameter)

     * @param growthRate Current growth rate of the plant (modified by this method)
     * @param currentWeather Current weather affecting the energy cycle
     * @return The amount to adjust the plant's health by (positive or negative)
     */
    float performEnergyCycle(float growthRate);
    
    /**
     * Handles time of day changes and determines if a state transition is needed.
     * For example, transitions from photosynthesis to respiration at nightfall.
     * 
     * @param plant The plant affected by the time change
     * @param timeOfDay The new time of day
     * @return The appropriate state after handling time change (this or new state)
     */
    EnergyCycleState handleTimeOfDayChange(Plant plant, TimeOfDay timeOfDay);
}

