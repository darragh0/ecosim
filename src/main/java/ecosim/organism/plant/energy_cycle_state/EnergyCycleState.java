package ecosim.organism.plant.energy_cycle_state;


import ecosim.enm.Weather;


/*
 * This interface is used to represent the energy cycle state of a plant.
 * Author: @MiaBorkoo
 */
public interface EnergyCycleState {
    void performEnergyCycle(float growthRate, Weather currentWeather);

}
