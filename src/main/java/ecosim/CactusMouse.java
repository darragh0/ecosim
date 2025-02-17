package ecosim;

public class CactusMouse extends Herbivore {
    public CactusMouse(int x, int y) {
        super(10, x, y, 5, Size.SMALL, ActivityType.NOCTURNAL, true);
    }

    @Override
    public void makeSound() {
        System.out.println("Squeal!");
    }
}
