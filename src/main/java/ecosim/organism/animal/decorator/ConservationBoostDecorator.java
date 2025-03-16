package ecosim.organism.animal.decorator;

import ecosim.organism.animal.abs.Animal;

/**
 * Decorator class that enhances the health conservation of an animal when it moves.
 * 
 * @author jjola00
 */
public class ConservationBoostDecorator extends AnimalDecorator {

    public ConservationBoostDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public void reduceHealth(float amount) {
        animal.reduceHealth(amount * 0.5f);  // Reduce damage by 50%
    }

    @Override
    public Animal clone() {
        return new ConservationBoostDecorator(animal.clone());
    }
}