package ecosim.organism.animal.decorator;

import ecosim.organism.animal.Animal;
import ecosim.organism.animal.conscious_state.Conscious;

/**
 * Decorator class that enhances the health conservation
 * of an animal when it moves.
 * 
 * @author jjola00
 */
public class HighConservationDecorator extends AnimalDecorator {

    public HighConservationDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public void move() {
        super.move();

        if (this.animal.getConsciousState() instanceof Conscious) {
            float maxHealth = this.animal.getMaxHealth();
            float currentHealth = this.animal.getHealth();
            float restoration = maxHealth * 0.05f;
            
            this.animal.setHealth(currentHealth + restoration);
        }
    }
}
