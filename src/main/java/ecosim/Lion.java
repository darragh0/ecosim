package ecosim;

public class Lion extends Carnivore {
    public Lion(int x, int y) {
        super(30, x, y, 15, Size.LARGE, ActivityType.DIURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }
}
