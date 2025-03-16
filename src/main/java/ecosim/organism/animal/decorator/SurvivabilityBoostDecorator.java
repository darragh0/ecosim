package ecosim.organism.animal.decorator;

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
        if (Math.random() < prey.getSurvivalChance()) {
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