package ecosim;

public class Owl extends Carnivore {
    public Owl(int x, int y) {
        super(10, x, y, 5, Size.SMALL, ActivityType.NOCTURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Hoo!");
    }
}
