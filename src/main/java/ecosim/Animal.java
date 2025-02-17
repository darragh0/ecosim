package ecosim;

public abstract class Animal extends Organism {
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
    protected float survivalChance;
    protected float reproductiveChance;

    public Animal(float maxHealth, int x, int y, int nutritionalValue, Size size, Diet diet, ActivityType activityType,
            boolean canHibernate) {
        super(maxHealth, x, y, nutritionalValue);
        this.size = size;
        this.diet = diet;
        this.activityType = activityType;
        this.canHibernate = canHibernate;
        this.awakeState = new Conscious();
        this.survivalChance = 0.5f;
        this.reproductiveChance = 0.5f;
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

    public float getSurvivalChance() {
        return survivalChance;
    }

    public float getReproductiveChance() {
        return reproductiveChance;
    }
}