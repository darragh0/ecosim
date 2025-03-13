package ecosim.organism.plant.energy_cycle_state;


import ecosim.enm.Weather;


// plants will respire at night -> night state
public class Respiration implements EnergyCycleState {
    @Override
    public void performEnergyCycle(float growthRate, Weather currentWeather) {
        // Decrease growth rate by a fixed percentage during respiration at night
        float decreaseFactor = 0.1f; // Decrease growth rate by 10%
        float adjustedGrowthRate = growthRate * (1 - decreaseFactor);

        System.out
            .println("Performing respiration. Growth rate decreased from " + growthRate + " to " + adjustedGrowthRate);

        growthRate = adjustedGrowthRate; // Update the growth rate with the new value
    }


}

