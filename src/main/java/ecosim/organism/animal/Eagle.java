package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;


public class Eagle extends Carnivore {

    public Eagle(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.DIURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }

}
