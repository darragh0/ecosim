package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.enm.Diet;

/**
 * Represents a Lion, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lion extends GrasslandAnimal {
    private static int lionCount = 0;

    public Lion() {
        super(Size.LARGE, Diet.CARNIVORE, ActivityType.DIURNAL, false);
        this.name = "Lion (" + ++lionCount + ")";
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
