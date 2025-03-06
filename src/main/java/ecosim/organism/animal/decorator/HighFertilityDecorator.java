package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;
import ecosim.attrs.Observable;

/**
 * Decorator class that enhances the reproductive chance
 * of an animal, indicating high fertility.
 * 
 * @author jjola00
 */
public class HighFertilityDecorator extends AnimalDecorator {

    public HighFertilityDecorator(Animal animal, Observable observable) {
        super(animal, observable);
        this.reproductiveChance += 0.2f;
    }

}
