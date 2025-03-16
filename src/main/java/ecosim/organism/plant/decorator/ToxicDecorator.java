package ecosim.organism.plant.decorator;

import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;
import ecosim.man.LoggerMan;
import ecosim.map.AnimalThreadLocal;

import java.util.logging.Level;

public class ToxicDecorator extends PlantDecorator {
    
    private static final float TOXICITY_DAMAGE = 3.0f;
    
    public ToxicDecorator(Plant decoratedPlant) {
        super(decoratedPlant);
    }
    
    @Override
    public float getNutritionalValue() {
        // Get the standard nutritional value
        float baseValue = super.getNutritionalValue();
        
        // Get the current animal from the thread local storage
        Animal eatingAnimal = AnimalThreadLocal.getCurrentAnimal();
        if (eatingAnimal != null) {
            // Apply toxic effect
            eatingAnimal.reduceHealth(TOXICITY_DAMAGE);
            LoggerMan.log(Level.INFO, eatingAnimal.getName() + " was harmed by toxic " + getName());
        }
        
        return baseValue;
    }
    
    @Override
    public String getName() {
        return "Toxic " + decoratedPlant.getName();
    }
    
    @Override
    public Plant clone() {
        return new ToxicDecorator(decoratedPlant.clone());
    }

    
}