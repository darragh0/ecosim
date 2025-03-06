package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;

/**
 * Decorator class that enhances the reproductive chance
 * of an animal, indicating high fertility.
 * 
 * @author jjola00
 */
public abstract class HighFertilityDecorator extends AnimalDecorator {

    public HighFertilityDecorator(Animal animal) {
        super(animal);
        this.reproductiveChance += 0.2f;
    }

}
