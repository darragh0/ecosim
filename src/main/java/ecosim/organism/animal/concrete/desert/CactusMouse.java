package ecosim.organism.animal.concrete.desert;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents a Cactus Mouse, a nocturnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class CactusMouse extends DesertAnimal {
    private static int cactusMouseCount = 0;

    public CactusMouse() {
        super(Size.SMALL, Diet.HERBIVORE, ActivityType.NOCTURNAL, false, ++cactusMouseCount);
        super.symbol = "🐭";
    }

    @Override
    public String getSound() {
        return "Squeal!";
    }

    @Override
    public CactusMouse clone() {
        return new CactusMouse();
    }

}
