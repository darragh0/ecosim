package ecosim.organism.animal;


import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.organism.animal.type.Carnivore;


public class Camel extends Carnivore {
    private static int camelCount = 0;

    public Camel(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.DIURNAL, false);
        this.name = "Camel (" + ++camelCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Grunt!");
    }

}
