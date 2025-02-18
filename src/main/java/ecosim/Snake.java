package ecosim;

public class Snake extends Carnivore {
    public Snake(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.NOCTURNAL, true);
    }

    @Override
    public void makeSound() {
        System.out.println("Hiss!");
    }
}
