package ecosim;

public class Deer extends Herbivore {
    public Deer(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.DIURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Bleat!");
    }
}
