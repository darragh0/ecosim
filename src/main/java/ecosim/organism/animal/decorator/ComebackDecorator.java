package ecosim.organism.animal.decorator;
import ecosim.organism.animal.Animal;

/**
 * Decorator class that enhances the animals survival chance based on max health.
 * 
 * @author jjola00
 */
public class ComebackDecorator extends AnimalDecorator {

    public ComebackDecorator(Animal animal) {
        super(animal);
    }
    public void comeback() {
        if (this.getHealth() <= 0.3f) {
            System.out.println("Comeback!");
            this.survivalChance += 0.2f;
        }
    }
}
