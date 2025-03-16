package ecosim.organism.plant.energy_cycle_state;

import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.Plant;

/**
 * This interface is used to represent the energy cycle state of a plant.
 * @author MiaBorkoo
 */
public interface EnergyCycleState {
    /**
     * Perform energy cycle and return the health adjustment
     * @param growthRate Current growth rate
     * @param currentWeather Current weather
     * @return The amount to adjust the plant's health by
     */
    float performEnergyCycle(float growthRate, Weather currentWeather);

    /**
     * Handle time of day changes and determine if a state transition is needed
     * 
     * @param plant The plant affected by the time change
     * @param timeOfDay The new time of day
     * @return The appropriate state after handling time change
     */
    EnergyCycleState handleTimeOfDayChange(Plant plant, TimeOfDay timeOfDay);
}
