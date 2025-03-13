package ecosim.organism.animal;

import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

/**
 * Represents a Lizard, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lizard extends DesertAnimal {
    private static int lizardCount = 0;

    public Lizard() {
        super(Size.SMALL, Diet.CARNIVORE, ActivityType.DIURNAL, true, "Lizard (" + ++lizardCount + ")");
    }

    @Override
    public void makeSound() {
        System.out.println("Chirp!");
    }
    @Override
    public Lizard clone() {
        return new Lizard();
    }
}
