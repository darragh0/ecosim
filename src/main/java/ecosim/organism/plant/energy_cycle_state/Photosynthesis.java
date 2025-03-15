package ecosim.organism.plant.energy_cycle_state;


// Plants will photosynthesize at day -> day state
import ecosim.enm.Weather;


public class Photosynthesis implements EnergyCycleState {
    @Override
    public void performEnergyCycle(float growthRate, Weather currentWeather) {
        float adjustedGrowthRate;

        // Adjust growth rate based on weather conditions
        adjustedGrowthRate = switch (currentWeather) {
            case SUNNY -> growthRate * 1.15f;
            case DRY -> growthRate * 1.07f;
            default -> growthRate * 1.02f;
        }; // Increase growth rate by 15% if sunny
        // Increase growth rate by 7% if dry
        // Increase growth rate by 2% for all other weather conditions

        System.out.println("Performing photosynthesis. Growth rate increased by: " + adjustedGrowthRate);

        growthRate += adjustedGrowthRate; // Update the growth rate after photosynthesis is performed

    }

}
