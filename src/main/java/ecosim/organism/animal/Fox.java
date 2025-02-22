package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;


public class Fox extends Carnivore {

    public Fox(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.NOCTURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Ring-ding-ding-ding-dingeringeding! Wa-pa-pa-pa-pa-pa-pow!");
    }

}
