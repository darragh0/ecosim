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
    protected float getWeatherGrowthAdjustment(Weather weather) {
        return switch (weather) {
            case SUNNY -> 0.1f;
            case RAINY -> 0.05f;
            case DRY -> -0.2f;
            case CLOUDY -> 0.02f;
            case SNOWY -> -0.3f;
        };
    }

    @Override
    public Plant clone() {
        DesertPlant clone = new DesertPlant();
        clone.setName(SpeciesNumbering.generateCloneName(this.getName()));
        return clone;
    }

}
