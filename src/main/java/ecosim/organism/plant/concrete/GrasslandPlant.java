package ecosim.organism.plant.concrete;

import ecosim.enm.Weather;
import ecosim.misc.SpeciesNumbering;
import ecosim.organism.plant.abs.Plant;

/**
 * Abstract class for all grassland plants in the ecosystem simulation.
 * 
 * This class provides grassland-specific implementation of growth rate adjustments
 * based on weather conditions. Grassland plants generally favor a balance of
 * sun and rain, and suffer significantly during dry periods.
 * 
 * @author MiaBorkoo
 */
public class GrasslandPlant extends Plant {

    /**
     * Updates the growth rate of grassland plants based on current weather.
     * 
     * @param currentWeather Current weather condition
     */
    @Override
    public void updateGrowthRate(Weather currentWeather) {
        float growthAdjustment = 0.0f;

        switch (currentWeather) {
            case SUNNY -> growthAdjustment = 0.08f; // Increase growth rate by 8% if sunny
            case RAINY -> growthAdjustment = 0.12f; // Increase growth rate by 12% if rainy
            case DRY -> growthAdjustment = -0.25f; // Decrease growth rate by 25% if dry
            case CLOUDY -> growthAdjustment = 0.04f; // Increase growth rate by 4% if cloudy
            case SNOWY -> growthAdjustment = -0.15f; // Decrease growth rate by 15% if snowy
        }

        // Update the growth rate based on the adjustment
        this.growthRate += this.growthRate * growthAdjustment;
    }

    @Override
    public Plant clone() {
        GrasslandPlant clone = new GrasslandPlant();
        clone.setName(SpeciesNumbering.generateCloneName(this.getName()));
        return clone;
    }

}
