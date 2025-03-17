package ecosim.organism.plant.concrete;

import ecosim.enm.Weather;
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

    public GrasslandPlant() {
        super();
    }

    public GrasslandPlant(GrasslandPlant source) {
        super(source);
    }

    /**
     * Updates the growth rate of grassland plants based on current weather.
     * 
     * @param currentWeather Current weather condition
     */
    @Override
    protected float getWeatherGrowthAdjustment(Weather weather) {
        return switch (weather) {
            case SUNNY -> 0.08f;
            case RAINY -> 0.12f;
            case DRY -> -0.25f;
            case CLOUDY -> 0.04f;
            case SNOWY -> -0.15f;
        };
    }

    @Override
    public Plant clone() {
        return new GrasslandPlant(this);
    }

}
