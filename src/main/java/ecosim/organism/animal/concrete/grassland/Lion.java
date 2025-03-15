package ecosim.organism.animal.concrete.grassland;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents a Lion, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lion extends GrasslandAnimal {
    private static int lionCount = 0;

    public Lion() {
        super(Size.LARGE, Diet.CARNIVORE, ActivityType.DIURNAL, false, ++lionCount);
        super.symbol = "🦁";
    }

    @Override
    public String getSound() {
        return "Roar!";
    }

    @Override
    public Lion clone() {
        return new Lion();
    }

}
