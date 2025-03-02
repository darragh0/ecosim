package ecosim.organism.animal.decorator;
import ecosim.organism.animal.Animal;

/**
 * Decorator class that enhances the animals survival chance based on current health.
 * 
 * @author jjola00
 */
public class ComebackDecorator extends AnimalDecorator {

    public ComebackDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public float getSurvivalChance() {
        if (this.getHealth() <= 0.3f) {
            System.out.println("Comeback!");
            return this.survivalChance += 0.2f;
        }
        return this.survivalChance;
    }
}
