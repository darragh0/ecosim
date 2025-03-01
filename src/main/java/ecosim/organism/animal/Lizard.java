package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;

/**
 * Represents a Lizard, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lizard extends Carnivore {
    private static int lizardCount = 0;

    public Lizard(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.DIURNAL, true);
        this.name = "Lizard (" + ++lizardCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Chirp!");
    }

}
