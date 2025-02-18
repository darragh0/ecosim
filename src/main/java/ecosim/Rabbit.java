package ecosim;

public class Rabbit extends Herbivore {
    public Rabbit(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.DIURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Squeak!");
    }
}
