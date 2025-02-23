package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Herbivore;


public class CactusMouse extends Herbivore {
    private static int cactusMouseCount = 0;

    public CactusMouse(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.NOCTURNAL, true);
        this.name = "Cactus Mouse (" + ++cactusMouseCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Squeal!");
    }

}
