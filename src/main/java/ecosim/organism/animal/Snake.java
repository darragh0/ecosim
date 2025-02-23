package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;


public class Snake extends Carnivore {
    private static int snakeCount = 0;

    public Snake(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.NOCTURNAL, true);
        this.name = "Snake (" + ++snakeCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Hiss!");
    }

}
