package ecosim;

public abstract class Herbivore extends Animal {
    public Herbivore(int x, int y, Size size, ActivityType activityType, boolean canHibernate) {
        super(x, y, size, Diet.CARNIVORE, activityType, canHibernate);
    }

    @Override
    public boolean isEdible(Organism organism) {
        return true; // Implement logic later
    }

    @Override
    public void eat() {
        // Implement eating behavior
    }
}
