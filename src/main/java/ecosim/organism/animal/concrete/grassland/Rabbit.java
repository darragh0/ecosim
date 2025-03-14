package ecosim.organism.animal.concrete.grassland;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents a Rabbit, a diurnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Rabbit extends GrasslandAnimal {
    private static int rabbitCount = 0;

    public Rabbit() {
        super(Size.SMALL, Diet.HERBIVORE, ActivityType.DIURNAL, false, ++rabbitCount);
        super.symbol = "🐇";
    }

    @Override
    public void makeSound() {
        System.out.println("Squeak!");
    }

    @Override
    public Rabbit clone() {
        return new Rabbit();
    }

}
