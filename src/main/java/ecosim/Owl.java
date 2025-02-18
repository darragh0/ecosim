package ecosim;

public class Owl extends Carnivore {
    public Owl(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.NOCTURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Hoo!");
    }
}
