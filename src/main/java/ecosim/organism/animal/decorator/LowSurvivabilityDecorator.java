package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;

/**
 * Decorator class that reduces the survival chance
 * of an animal, indicating low survivability.
 * 
 * @author jjola00
 */
public class LowSurvivabilityDecorator extends AnimalDecorator {

    public LowSurvivabilityDecorator(Animal animal) {
        super(animal);
        this.survivalChance -= 0.2f;
    }

}
