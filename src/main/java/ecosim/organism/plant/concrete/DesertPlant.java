package ecosim.organism.plant.concrete;

import ecosim.enm.Weather;
import ecosim.misc.SpeciesNumbering;
import ecosim.organism.plant.abs.Plant;

/**
 * Abstract class for all desert plants in the ecosystem simulation.
 * 
 * This class provides desert-specific implementation of growth rate adjustments
 * based on weather conditions. Desert plants generally favor sunny weather
 * and are less affected by dry conditions than other plant types.
 * 
 * @author MiaBorkoo
 */
public class DesertPlant extends Plant {

    /**
     * Updates the growth rate of desert plants based on current weather.
     * Desert plants have specialized responses to different weather conditions, e.g Snowy weather is bad for them.
     * 
     * @param currentWeather Current weather condition
     */
    @Override
    public void updateGrowthRate(Weather currentWeather) {
        float growthAdjustment = 0.0f;

        switch (currentWeather) {
            case SUNNY -> growthAdjustment = 0.1f; // Increase growth rate by 10% if sunny
            case RAINY -> growthAdjustment = 0.05f; // Increase growth rate by 5% if rainy
            case DRY -> growthAdjustment = -0.2f; // Decrease growth rate by 20% if dry
            case CLOUDY -> growthAdjustment = 0.02f; // Increase growth rate by 2% if cloudy
            case SNOWY -> growthAdjustment = -0.3f; 
        }

        
        this.growthRate += this.growthRate * growthAdjustment;
    }

    @Override
    public Plant clone() {
        DesertPlant clone = new DesertPlant();
        clone.setName(SpeciesNumbering.generateCloneName(this.getName()));
        return clone;
    }

}
