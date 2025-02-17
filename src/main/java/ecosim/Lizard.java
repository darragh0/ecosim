package ecosim;

public class Lizard extends Carnivore {
    public Lizard(int x, int y) {
        super(10, x, y, 5, Size.SMALL, ActivityType.DIURNAL, true);
    }

    @Override
    public void makeSound() {
        System.out.println("Chirp!");
    }
}
