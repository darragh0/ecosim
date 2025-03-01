package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;

/**
 * Represents an Owl, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Owl extends Carnivore {
    private static int owlCount = 0;

    public Owl(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.NOCTURNAL, false);
        this.name = "Owl (" + ++owlCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Hoo!");
    }

}
