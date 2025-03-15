package ecosim.organism.plant.energy_cycle_state;

import ecosim.enm.Weather;

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
}
