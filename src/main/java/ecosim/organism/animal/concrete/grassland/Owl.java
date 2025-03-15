package ecosim.organism.animal.concrete.grassland;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents an Owl, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Owl extends GrasslandAnimal {
    private static int owlCount = 0;

    public Owl() {
        super(Size.SMALL, Diet.CARNIVORE, ActivityType.NOCTURNAL, false, ++owlCount);
        super.symbol = "🦉";
    }

    @Override
    public String makeSound() {
        return "Hoo!";
    }

    @Override
    public Owl clone() {
        return new Owl();
    }

}
