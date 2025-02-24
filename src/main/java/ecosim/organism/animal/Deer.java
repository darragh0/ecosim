package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Herbivore;


public class Deer extends Herbivore {
    private static int deerCount = 0;

    public Deer(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.DIURNAL, false);
        this.name = "Deer (" + ++deerCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Bleat!");
    }

}
