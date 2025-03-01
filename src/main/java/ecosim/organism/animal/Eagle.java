package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;

/**
 * Represents an Eagle, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Eagle extends Carnivore {
    private static int eagleCount = 0;

    public Eagle(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.DIURNAL, false);
        this.name = "Eagle (" + ++eagleCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }

}
