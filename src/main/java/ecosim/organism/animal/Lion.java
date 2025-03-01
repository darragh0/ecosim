package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;

/**
 * Represents a Lion, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lion extends Carnivore {
    private static int lionCount = 0;

    public Lion(int x, int y) {
        super(x, y, Size.LARGE, ActivityType.DIURNAL, false);
        this.name = "Lion (" + ++lionCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }

}
