package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;


public class Snake extends Carnivore {

    public Snake(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.NOCTURNAL, true);
    }

    @Override
    public void makeSound() {
        System.out.println("Hiss!");
    }

}
