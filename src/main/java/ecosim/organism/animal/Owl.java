package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

/**
 * Represents an Owl, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Owl extends GrasslandAnimal {
    private static int owlCount = 0;

    public Owl() {
        super(Size.SMALL, Diet.CARNIVORE, ActivityType.NOCTURNAL, false, "Owl (" + ++owlCount + ")");
    }

    @Override
    public void makeSound() {
        System.out.println("Hoo!");
    }
    @Override
    public Owl clone() {
        return new Owl();
    }
}
