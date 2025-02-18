package ecosim;

public class Camel extends Carnivore {
    public Camel(int x, int y) {
        super(x, y, Size.MEDIUM, ActivityType.DIURNAL, false);
    }

    @Override
    public void makeSound() {
        System.out.println("Grunt!");
    }
}
