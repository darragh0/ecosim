package ecosim.organism.animal.concrete.desert;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents a Snake, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Snake extends DesertAnimal {
    private static int snakeCount = 0;

    public Snake() {
        super(Size.MEDIUM, Diet.CARNIVORE, ActivityType.NOCTURNAL, true, ++snakeCount);
        super.symbol = "🐍";
    }

    @Override
    public String getSound() {
        return "Hiss!";
    }

    @Override
    public Snake clone() {
        return new Snake();
    }

}
