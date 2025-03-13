package ecosim.organism.animal.concrete.desert;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents an Eagle, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Eagle extends DesertAnimal {
    private static int eagleCount = 0;

    public Eagle() {
        super(Size.MEDIUM, Diet.CARNIVORE, ActivityType.DIURNAL, false, ++eagleCount);
    }

    @Override
    public void makeSound() {
        System.out.println("Sqwuak!");
    }

    @Override
    public Eagle clone() {
        return new Eagle();
    }

}
