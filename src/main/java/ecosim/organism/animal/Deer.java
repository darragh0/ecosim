package ecosim.organism.animal;

import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

/**
 * Represents a Deer, a diurnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Deer extends GrasslandAnimal {
    private static int deerCount = 0;

    public Deer() {
        super(Size.MEDIUM, Diet.HERBIVORE, ActivityType.DIURNAL, true);
        this.name = "Deer (" + ++deerCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Bleat!");
    }
    @Override
    public Deer clone() {
        return new Deer();
    }
}
