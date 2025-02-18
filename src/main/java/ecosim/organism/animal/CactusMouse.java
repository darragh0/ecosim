package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Herbivore;


public class CactusMouse extends Herbivore {

    public CactusMouse(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.NOCTURNAL, true);
    }

    @Override
    public void makeSound() {
        System.out.println("Squeal!");
    }

}
