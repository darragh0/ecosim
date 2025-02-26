package ecosim.organism.animal;


import ecosim.attrs.Observer;
import ecosim.enm.*;
import ecosim.organism.Organism;
import ecosim.organism.animal.conscious_state.Conscious;
import ecosim.organism.animal.conscious_state.ConsciousState;


public abstract class Animal extends Organism implements Observer {

    protected Size size;
    protected Diet diet;
    protected ActivityType activityType;
    protected ConsciousState awakeState;
    protected boolean canHibernate;
    protected float survivalChance;
    protected float reproductiveChance;

    public Animal(int x, int y, Size size, Diet diet, ActivityType activityType, boolean canHibernate) {
        super(size.getMaxHealth(), x, y, size.getNutritionalValue());
        this.size = size;
        this.diet = diet;
        this.activityType = activityType;
        this.canHibernate = canHibernate;
        this.awakeState = new Conscious();
        this.survivalChance = 0.5f;
        this.reproductiveChance = 0.5f;
    }

    public Animal(Animal animal) {
        this(animal.getX(), animal.getY(), animal.size, animal.diet, animal.activityType, animal.canHibernate);
    }

    public Diet getDiet() {
        return this.diet;
    }

    public Size getSize() {
        return this.size;
    }

    public ActivityType getActivityType() {
        return this.activityType;
    }

    public void makeSound() {}

    public void eat() {}

    public void breed() {}

    public void move() {
        this.awakeState.move();
    }

    @Override
    public void update(Event event){
        if (event instanceof Season newSeason) {
            handleSeasonUpdate(newSeason);
        }
        else if (event instanceof TimeOfDay newTimeOfDay) {
            handleTimeOfDayUpdate(newTimeOfDay);
        }
    }

    public void handleSeasonUpdate(Season season){
        // TODO: Handle season changes
    }

    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay){
        // TODO: Handle time of day changes
    }

    public float getSurvivalChance() {
        return this.survivalChance;
    }

    public float getReproductiveChance() {
        return this.reproductiveChance;
    }

    public boolean isEdible(Organism organism) {
        return false;
    }

}
