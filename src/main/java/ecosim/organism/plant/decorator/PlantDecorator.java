package ecosim.organism.plant.decorator;

import ecosim.enm.Weather;
import ecosim.organism.plant.abs.Plant;

public abstract class PlantDecorator extends Plant {
    
    protected final Plant decoratedPlant;
    
    public PlantDecorator(Plant decoratedPlant) {
        this.decoratedPlant = decoratedPlant;
    }
    
    @Override
    public void beEaten() {
        decoratedPlant.beEaten();
    }
    
    @Override
    public float getNutritionalValue() {
        return decoratedPlant.getNutritionalValue();
    }
    
    @Override
    public String getName() {
        return decoratedPlant.getName();
    }
    
    @Override
    public Plant clone() {
        return decoratedPlant.clone();
    }
    @Override
    public void updateGrowthRate(Weather weather) {
        decoratedPlant.updateGrowthRate(weather);
    }
}