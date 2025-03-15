package ecosim.organism.animal.concrete.desert;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents a Lizard, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lizard extends DesertAnimal {
    private static int lizardCount = 0;

    public Lizard() {
        super(Size.SMALL, Diet.CARNIVORE, ActivityType.DIURNAL, true, ++lizardCount);
        super.symbol = "🦎";
    }

    @Override
    public String makeSound() {
        return "Chirp!";
    }

    @Override
    public Lizard clone() {
        return new Lizard();
    }

}
