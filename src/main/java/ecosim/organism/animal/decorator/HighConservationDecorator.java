package ecosim.organism.animal.decorator;

import ecosim.organism.animal.Animal;
import ecosim.attrs.Observable;

/**
 * Decorator class that enhances the health conservation
 * of an animal when it moves.
 * 
 * @author jjola00
 */
 public class HighConservationDecorator extends AnimalDecorator {
    public HighConservationDecorator(Animal animal, Observable observable) {
        super(animal, observable);
    }

    @Override
    public void reduceHealth(float amount) {
        super.reduceHealth(amount * 0.5f); 
    }
}
