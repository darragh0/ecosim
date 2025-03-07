package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.enm.Diet;

/**
 * Represents a Cactus Mouse, a nocturnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class CactusMouse extends DesertAnimal {
    private static int cactusMouseCount = 0;

    public CactusMouse() {
        super(Size.SMALL, Diet.HERBIVORE, ActivityType.NOCTURNAL, true);
        this.name = "Cactus Mouse (" + ++cactusMouseCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Squeal!");
    }

}
