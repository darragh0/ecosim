package ecosim;

public abstract class Omnivore extends Animal {
    public Omnivore(float maxHealth, int x, int y, int nutritionalValue, Size size, ActivityType activityType, boolean canHibernate) {
        super(maxHealth, x, y, nutritionalValue, size, Diet.HERBIVORE, activityType, canHibernate);
    }

    public boolean isEdible(Organism organism) {
        return true; //implement later
    }
    public void eat() {
    }
}
