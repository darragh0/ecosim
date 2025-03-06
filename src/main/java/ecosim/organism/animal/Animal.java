package ecosim.organism.animal;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.*;
import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.enm.ActivityState;
import ecosim.map.Map;
import ecosim.organism.Organism;
import ecosim.organism.animal.conscious_state.Conscious;
import ecosim.organism.animal.conscious_state.ConsciousState;


/**
 * Abstract class representing an animal in the ecosystem,
 * defining its properties, behaviors, and interactions.
 * 
 * @author jjola00
 */
public abstract class Animal extends Organism implements Observer {

    protected Diet diet;
    protected ActivityType activityType;
    protected ConsciousState consciousState;
    protected ActivityState activityState; 
    protected boolean canHibernate;
    protected float survivalChance;
    protected float reproductiveChance;

    public Animal(int x, int y, Size size, Diet diet, ActivityType activityType, boolean canHibernate) {
        super(size, size.getMaxHealth(), x, y, size.getNutritionalValue());
        this.diet = diet;
        this.activityType = activityType;
        this.consciousState = new Conscious();
        this.survivalChance = 0.5f;
        this.reproductiveChance = 0.5f;
        this.activityState = ActivityState.SLEEPING;  
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

    public ActivityState getActivityState() { 
        return this.activityState;
    }

    public void setSleepState(ActivityState activityState) { 
        this.activityState = activityState;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void makeSound() {}

    public void eat() {}

    public void breed() {}

    public abstract String move();

    @Override
    public void update(Observable observable){
        Event event = observable.getCurrentState();
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

    public ConsciousState getConsciousState() {
        return this.consciousState;
    }

    public void setConsciousState(ConsciousState consciousState) {
        this.consciousState = consciousState;
    }

    public boolean isEdible(Organism organism) {
        return false;
    }

    public void iWasGonnaCallThisFunctionMoveButICANTBecauseSomeoneElseAlreadyTookTheNameMoveSoIAmLeftToComeUpWithAnotherNameForThisMethodWhichHasUnfortunatelyBecomeVeryLongAsAResultOfTheFactThatSomebodyHasReservedTheMoveNameSoNowImSadAndHeresALongMethodName() {
        Map.getInstance().move(this);
    }

    @Override
    public void setCoords(int x, int y) {
        this.coords.setX(x);
        this.coords.setY(y);
    }

    public void reduceHealth(float amount) {
        this.health = Math.max(0, this.health - amount);
    }

    public void restoreHealth(float amount) {
        this.health = Math.min(maxHealth, this.health + amount); 
    }

}
