package ecosim;

public class Snake extends Carnivore {
    public Snake(int x, int y) {
        super(20, x, y, 10, Size.MEDIUM, ActivityType.NOCTURNAL, true);
    }

    @Override
    public void makeSound() {
        System.out.println("Hiss!");
    }
}
