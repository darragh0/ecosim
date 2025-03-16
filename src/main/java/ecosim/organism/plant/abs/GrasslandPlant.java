package ecosim.organism.plant.abs;


import ecosim.enm.Weather;


/**
 * This is the abstract class for all grassland plants.
 * It extends the Plant class and includes methods specific to grassland plants.
 * 
 * @author MiaBorkoo
 */
public abstract class GrasslandPlant extends Plant {

    public GrasslandPlant(int num) {
        super(num);
    }

    @Override
    public void updateGrowthRate(Weather currentWeather) {
        float growthAdjustment = 0.0f;

        switch (currentWeather) {
            case SUNNY -> growthAdjustment = 0.2f; // Increase growth rate by 20% if sunny
            case RAINY -> growthAdjustment = 0.15f; // Increase growth rate by 15% if rainy
            case DRY -> growthAdjustment = -0.1f; // Decrease growth rate by 10% if dry
            case CLOUDY -> growthAdjustment = 0.05f; // Increase growth rate by 5% if cloudy
            case SNOWY -> growthAdjustment = -0.2f; // Decrease growth rate by 20% if snowy
        }

        // Update the growth rate based on the adjustment
        this.growthRate += this.growthRate + growthAdjustment;
    }

}
