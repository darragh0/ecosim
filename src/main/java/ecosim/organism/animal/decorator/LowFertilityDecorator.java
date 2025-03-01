package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;

/**
 * Decorator class that reduces the reproductive chance
 * of an animal, indicating low fertility.
 * 
 * @author jjola00
 */
public class LowFertilityDecorator extends AnimalDecorator {

    public LowFertilityDecorator(Animal animal) {
        super(animal);
        this.reproductiveChance -= 0.2f;
    }

}
