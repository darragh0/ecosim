package ecosim;

public class CactusMouse extends Herbivore {
    public CactusMouse(int x, int y) {
        super(x, y, Size.SMALL, ActivityType.NOCTURNAL, true);
    }

    @Override
    public void makeSound() {
        System.out.println("Squeal!");
    }
}
