package ecosim.organism.animal.decorator;

import static ecosim.common.Util.randFloat;
import ecosim.organism.animal.abs.Animal;

/**
 * Decorator class that enhances the reproductive chance of an animal.
 * 
 * @author jjola00
 */
public class FertilityBoostDecorator extends AnimalDecorator {

    public FertilityBoostDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public float getReproductiveChance() {
        return animal.getReproductiveChance() + 0.2f;  // Boost by 0.2
    }

    @Override
    public Animal breed(Animal mate) {
        float boostedChance = getReproductiveChance();
        float mateChance = mate.getReproductiveChance();
        float combinedChance = Math.max(boostedChance, mateChance);
        if (randFloat(0.0f, 1.0f) < combinedChance) {
            return animal.clone();
        }
        return null;
    }

    @Override
    public Animal clone() {
        return new FertilityBoostDecorator(animal.clone());
    }
}