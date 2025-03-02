package ecosim.organism.plant;

// Plants will photosynthesize at day -> day state
import ecosim.enm.Weather;

public class Photosynthesis implements EnergyCycleState {
    @Override
    public void performEnergyCycle(float growthRate, Weather currentWeather) {
        float adjustedGrowthRate;

        // Adjust growth rate based on weather conditions
        switch (currentWeather) {
            case SUNNY:
                adjustedGrowthRate = growthRate * 1.15f; // Increase growth rate by 15% if sunny
                break;
            case DRY:
                adjustedGrowthRate = growthRate * 1.07f; // Increase growth rate by 7% if dry
                break;
            default:
                adjustedGrowthRate = growthRate * 1.02f; // Increase growth rate by 2% for all other weather conditions
                break;
                
        }

        System.out.println("Performing photosynthesis. Growth rate increased by: " + adjustedGrowthRate);
        
        growthRate += adjustedGrowthRate; // Update the growth rate after photosynthesis is performed
        
    }
}
