package ecosim.organism.plant.energy_cycle_state;

import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.organism.plant.abs.Plant;

// plants will respire at night -> night state
public class RespirationState implements EnergyCycleState {
    @Override
    public float performEnergyCycle(float growthRate, Weather currentWeather) {
        float adjustedGrowthRate;
        
        // Handle both growth and health adjustments
        switch (currentWeather) {
            case SUNNY -> {
                adjustedGrowthRate = growthRate * -0.05f; // Minor growth decrease even in good conditions
            }
            case DRY -> {
                adjustedGrowthRate = growthRate * -0.15f; // More significant growth decrease
            }
            case RAINY -> {
                adjustedGrowthRate = growthRate * -0.08f; // Moderate growth decrease
            }
            case SNOWY -> {
                adjustedGrowthRate = growthRate * -0.25f; // Significant growth decrease
            }
            default -> {
                adjustedGrowthRate = growthRate * -0.1f; // Default growth decrease
            }
        }
        
        

        return growthRate * adjustedGrowthRate; // Update the growth rate
    }

    @Override
    public EnergyCycleState handleTimeOfDayChange(Plant plant, TimeOfDay timeOfDay) {
        if (timeOfDay == TimeOfDay.DAY) {
            return new PhotosynthesisState();
        }
        return this; // Stay in current state
    }
}

