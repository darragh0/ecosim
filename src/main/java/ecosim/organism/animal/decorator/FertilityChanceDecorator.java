package ecosim.organism.animal.decorator;

import ecosim.organism.animal.Animal;

/**
 * Decorator class for enhancing the reproductive chance of animals,
 * based on their maximum health.
 * 
 * @author jjola00
 */
public class FertilityChanceDecorator extends AnimalDecorator {
    public FertilityChanceDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public float getReproductiveChance() {
        return this.getReproductiveChance() + (this.getMaxHealth() * 0.01f); 
    }
}
