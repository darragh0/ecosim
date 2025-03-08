package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.enm.Diet;

/**
 * Represents a Snake, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Snake extends DesertAnimal {
    private static int snakeCount = 0;

    public Snake() {
        super(Size.MEDIUM, Diet.CARNIVORE, ActivityType.NOCTURNAL, true);
        this.name = "Snake (" + ++snakeCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Hiss!");
    }
    @Override
    public Snake createClone() {
        return new Snake();
    }
}
