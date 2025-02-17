package ecosim;

public abstract class Animal extends Organism {
    protected enum Size {
        SMALL, MEDIUM, LARGE
    }

    protected enum ActivityType {
        DIURNAL, NOCTURNAL
    }

    protected enum Diet {
        HERBIVORE, CARNIVORE, OMNIVORE
    }

    protected Size size;
    protected Diet diet;
    protected ActivityType activityType;
    protected ConsciousState awakeState;
    protected boolean canHibernate;

    public Animal(float maxHealth, int x, int y, int nutritionalValue, Size size, Diet diet, ActivityType activityType,
            boolean canHibernate) {
        super(maxHealth, x, y, nutritionalValue);
        this.size = size;
        this.diet = diet;
        this.activityType = activityType;
        this.canHibernate = canHibernate;
        this.awakeState = new Conscious();
    }

    public Diet getDiet() {
        return diet;
    }

    public Size getSize() {
        return size;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void makeSound() {
    }

    public void eat() {
    }

    public void breed() {
    }
    
    public void update() {
    }

    public void move() {
        awakeState.move();
    }

}