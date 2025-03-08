package ecosim.organism.animal.decorator;

import ecosim.organism.animal.Animal;

/**
 * Decorator class that enhances the reproductive chance
 * of an animal, indicating high fertility.
 * 
 * @author jjola00
 */
public class FertilityBoostDecorator extends AnimalDecorator {

    public FertilityBoostDecorator(Animal animal) {
        super(animal);
        this.reproductiveChance += 0.2f;
    }
    @Override
    public Animal createClone() {
        return new FertilityBoostDecorator(animal.createClone());
    }
}
