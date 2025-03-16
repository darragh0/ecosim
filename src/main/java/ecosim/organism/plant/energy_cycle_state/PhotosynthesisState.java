package ecosim.organism.plant.energy_cycle_state;


import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.Plant;


public class PhotosynthesisState implements EnergyCycleState {
    @Override
    public float performEnergyCycle(float growthRate, Weather currentWeather) {
        float adjustedGrowthRate;

        // Both growth rate and health adjustments
        switch (currentWeather) {
            case SUNNY -> {
                adjustedGrowthRate = growthRate * 1.15f; // Good health increase during sunny conditions
            }
            case DRY -> {
                adjustedGrowthRate = growthRate * 1.07f; // Good health increase during dry conditions
            }
            case RAINY -> {
                adjustedGrowthRate = growthRate * 1.1f; // Good health increase during rainy conditions
            }
            case SNOWY -> {
                adjustedGrowthRate = growthRate * 0.8f; // Health decrease during snowy conditions
            }
            default -> {
                adjustedGrowthRate  = growthRate * 1.02f; // Default health increase
            }
        }

        
        return growthRate * adjustedGrowthRate; // Update the growth rate;
    }

    @Override
    public EnergyCycleState handleTimeOfDayChange(Plant plant, TimeOfDay timeOfDay) {
        if (timeOfDay == TimeOfDay.NIGHT) {
            return new RespirationState();
        }
        return this; // Stay in current state
    }
}
