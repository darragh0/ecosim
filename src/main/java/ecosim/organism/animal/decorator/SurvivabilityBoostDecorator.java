package ecosim.organism.animal.decorator;

import ecosim.organism.animal.Animal;

/**
 * Decorator class that enhances the survival chance
 * of an animal, indicating high survivability.
 * 
 * @author jjola00
 */
public class SurvivabilityBoostDecorator extends AnimalDecorator {

    public SurvivabilityBoostDecorator(Animal animal) {
        super(animal);
        this.survivalChance += 0.2f;
    }
    @Override
    public Animal createClone() {
        return new SurvivabilityBoostDecorator(animal.createClone());
    }
}
