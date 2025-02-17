package ecosim;

public class Rabbit extends Herbivore {
    public Rabbit(int x, int y) {
        super(10, x, y, 5, Size.SMALL, ActivityType.DIURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Squeak!");
    }
}
