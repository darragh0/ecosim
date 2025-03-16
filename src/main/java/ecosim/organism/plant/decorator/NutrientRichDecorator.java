package ecosim.organism.plant.decorator;

import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;
import ecosim.enm.Weather;
import ecosim.man.LoggerMan;
import ecosim.map.AnimalThreadLocal;

import java.util.logging.Level;

public class NutrientRichDecorator extends PlantDecorator {
    
    private static final float NUTRITION_BONUS = 4.0f;
    
    public NutrientRichDecorator(Plant decoratedPlant) {
        super(decoratedPlant);
    }
    
    @Override
    public float getNutritionalValue() {
        // Get the original animal and add bonus nutrition
        float baseValue = super.getNutritionalValue();
        
        // Log the nutritional boost if we can identify the eating animal
        Animal eatingAnimal = AnimalThreadLocal.getCurrentAnimal();
        if (eatingAnimal != null) {
            LoggerMan.log(Level.INFO, eatingAnimal.getName() + " gained extra nutrition from " + getName());
        }
        
        return baseValue + NUTRITION_BONUS;
    }
    
    @Override
    public String getName() {
        return "Nutrient-Rich " + decoratedPlant.getName();
    }
    
    @Override
    public Plant clone() {
        return new NutrientRichDecorator(decoratedPlant.clone());
    }

    

   
}