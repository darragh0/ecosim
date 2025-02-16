package ecosim;

public abstract class Animal extends Organism {
    protected enum Size { SMALL, MEDIUM, LARGE }
    protected enum ActivityType { DIURNAL, NOCTURNAL }
    
    protected Size size;
    protected ActivityType activityType;
    protected ConsciousState awakeState;
    protected boolean canHibernate;

    public Animal(float maxHealth, int x, int y, int nutritionalValue, Size size, ActivityType activityType, boolean canHibernate) {
        super(maxHealth, x, y, nutritionalValue);
        this.size = size;
        this.activityType = activityType;
        this.canHibernate = canHibernate;
        this.awakeState = new Conscious(); // Default state
    }

    public void move() {
        awakeState.move();
    }

    public abstract void makeSound();
}
