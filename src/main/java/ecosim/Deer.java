package ecosim;

public class Deer extends Herbivore {
    public Deer(int x, int y) {
        super(20, x, y, 10, Size.MEDIUM, ActivityType.DIURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Bleat!");
    }
}
