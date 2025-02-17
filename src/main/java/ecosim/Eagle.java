package ecosim;

public class Eagle extends Carnivore {
    public Eagle(int x, int y) {
        super(20, x, y, 10, Size.MEDIUM, ActivityType.DIURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }
}
