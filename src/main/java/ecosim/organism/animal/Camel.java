package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

/**
 * Represents a Camel, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Camel extends DesertAnimal {
    private static int camelCount = 0;
    public Camel() {
        super(Size.MEDIUM, Diet.CARNIVORE, ActivityType.DIURNAL, false, ++camelCount);
    }

    @Override
    public void makeSound() {
        System.out.println("Grunt!");
    }
    @Override
    public Camel clone() {
        return new Camel();
    }

}
