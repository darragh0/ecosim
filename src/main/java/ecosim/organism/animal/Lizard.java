package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;


public class Lizard extends Carnivore {

    public Lizard(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.DIURNAL, true);
    }

    @Override
    public void makeSound() {
        System.out.println("Chirp!");
    }

}
