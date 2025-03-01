package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Herbivore;

/**
 * Represents a Rabbit, a diurnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Rabbit extends Herbivore {
    private static int rabbitCount = 0;

    public Rabbit(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.DIURNAL, false);
        this.name = "Rabbit (" + ++rabbitCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Squeak!");
    }

}
