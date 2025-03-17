package ecosim.organism.animal.decorator;

import static ecosim.common.Util.randFloat;
import ecosim.organism.animal.abs.Animal;

/**
 * Decorator class that enhances the survival chance of an animal.
 * 
 * @author jjola00
 */
public class SurvivabilityBoostDecorator extends AnimalDecorator {

    public SurvivabilityBoostDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public float getSurvivalChance() {
        return animal.getSurvivalChance() + 0.2f;  // Boost by 0.2
    }

    @Override
    public boolean eat(Animal prey) {
        if (randFloat(0.0f, 1.0f) < prey.getSurvivalChance()) {
            animal.restoreHealth(prey.getNutritionalValue());
            return true;
        }
        return false;
    }

    @Override
    public Animal clone() {
        return new SurvivabilityBoostDecorator(animal.clone());
    }
}