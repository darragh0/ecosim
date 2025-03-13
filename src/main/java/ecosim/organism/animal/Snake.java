package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

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
    }

    @Override
    public void makeSound() {
        System.out.println("Hiss!");
    }
    @Override
    public Snake clone() {
        return new Snake();
    }
}
