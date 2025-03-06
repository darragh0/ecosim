package ecosim.organism.animal.decorator;

import ecosim.organism.animal.Animal;

/**
 * Decorator class that enhances the health conservation
 * of an animal when it moves.
 * 
 * @author jjola00
 */
 public abstract class HighConservationDecorator extends AnimalDecorator {
    public HighConservationDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public void reduceHealth(float amount) {
        super.reduceHealth(amount * 0.5f); 
    }
}
