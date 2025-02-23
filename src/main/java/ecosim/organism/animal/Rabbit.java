package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Herbivore;


public class Rabbit extends Herbivore {
    private static int rabbitCount = 0;

    public Rabbit(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.DIURNAL, false);
        this.name = "Rabbit (" + ++rabbitCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Squeak!");
    }

}
