package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

/**
 * Represents a Lion, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lion extends GrasslandAnimal {
    private static int lionCount = 0;

    public Lion() {
        super(Size.LARGE, Diet.CARNIVORE, ActivityType.DIURNAL, false, "Lion (" + ++lionCount + ")");
    }

    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }
    @Override
    public Lion clone() {
        return new Lion();
    }
}
