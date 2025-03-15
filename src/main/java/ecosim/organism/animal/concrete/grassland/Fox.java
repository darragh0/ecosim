package ecosim.organism.animal.concrete.grassland;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents a Fox, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Fox extends GrasslandAnimal {
    private static int foxCount = 0;

    public Fox() {
        super(Size.MEDIUM, Diet.CARNIVORE, ActivityType.NOCTURNAL, false, ++foxCount);
        super.symbol = "🦊";
    }

    @Override
    public String getSound() {
        return "Ring-ding-ding-ding-dingeringeding! Wa-pa-pa-pa-pa-pa-pow!";
    }

    @Override
    public Fox clone() {
        return new Fox();
    }

}
