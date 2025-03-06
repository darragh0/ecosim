package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.enm.Diet;
import ecosim.attrs.Observable;

/**
 * Represents a Cactus Mouse, a nocturnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class CactusMouse extends DesertAnimal {
    private static int cactusMouseCount = 0;

    public CactusMouse(Observable observable) {
        super(Size.SMALL, Diet.HERBIVORE, ActivityType.NOCTURNAL, true, observable);
        this.name = "Cactus Mouse (" + ++cactusMouseCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Squeal!");
    }

}
