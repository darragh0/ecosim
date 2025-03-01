package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;

/**
 * Represents a Fox, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Fox extends Carnivore {
    private static int foxCount= 0;

    public Fox(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.NOCTURNAL, false);
        this.name = "Fox (" + ++foxCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Ring-ding-ding-ding-dingeringeding! Wa-pa-pa-pa-pa-pa-pow!");
    }

}
