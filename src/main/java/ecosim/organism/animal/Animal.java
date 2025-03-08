package ecosim.organism.animal;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.ActivityState;
import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Event;
import ecosim.enm.Season;
import ecosim.enm.Size;
import ecosim.enm.TimeOfDay;
import ecosim.map.Map;
import ecosim.organism.Organism;
import ecosim.organism.animal.conscious_state.Conscious;
import ecosim.organism.animal.conscious_state.ConsciousState;
import ecosim.organism.plant.Plant;

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

    public Animal(Size size, Diet diet, ActivityType activityType, boolean canHibernate) {
        super(size); // Default coordinates (0, 0)
        this.diet = diet;
        this.activityType = activityType;
        this.consciousState = new Conscious();
        this.survivalChance = 0.5f;
        this.reproductiveChance = 0.5f;
        this.activityState = ActivityState.SLEEPING;
    }

    public Animal(Animal animal) {
        super(animal.size);
        this.diet = animal.diet;
        this.activityType = animal.activityType;
        this.consciousState = new Conscious();
        this.survivalChance = animal.survivalChance;
        this.reproductiveChance = animal.reproductiveChance;
        this.activityState = animal.activityState;
        this.canHibernate = animal.canHibernate;
    }

    @Override
    public Animal clone(){
        Animal clone = createClone();
        clone.setHealth(this.getMaxHealth() / 2);
        return clone;
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

    public  void makeSound() {
        System.out.println("The animal makes a sound.");
    };

    public boolean canEatAnimal(Animal potentialPrey) {
        boolean isDietCompatible = this.diet != Diet.HERBIVORE;
        boolean isDifferentSpecies = this.getClass() != potentialPrey.getClass();
        boolean isPredatorLargeEnough = this.size.ordinal() >= potentialPrey.size.ordinal();
        
        return isDietCompatible && isDifferentSpecies && isPredatorLargeEnough;
    }
    
    public boolean canEatPlant() {
        return this.diet != Diet.CARNIVORE;
    }

    public boolean eat(Animal animal) {
       // Return true if the animal to be eaten didn't survive, otherwise return false.  
        boolean canEat = canEatAnimal(animal);
        if (!canEat) return false;

        // TODO: Animal at this stage can now be eaten, implement animal eating functionality.

        return false;
    };

    public boolean eat(Plant plant) {
       // Return true if the plant to be eaten has a biteCapacity of 0 after being eaten, otherwise return false.
       boolean canEat = canEatPlant();
        if (!canEat) return false;
       // TODO: Plant at this stage can now be eaten, implement plant eating functionality. 
       
       return false;
    };

    public void breed() {
        if (Math.random() < this.reproductiveChance) {
            Animal child = this.clone();
            Map.getInstance().add(child);
        }
    }

    protected abstract Animal createClone();

    public String move() {
        return this.consciousState.move(this);
    }

    @Override
    public void update(Observable observable) {
        Event event = observable.getCurrentState();
        switch (event) {
            case Season season -> handleSeasonUpdate(season);
            case TimeOfDay timeOfDay -> handleTimeOfDayUpdate(timeOfDay);
            default -> {
            }
        }
    }

    public void handleSeasonUpdate(Season season) {
        switch (season) {
            case Season.WINTER -> {if (canHibernate) {setSleepState(ActivityState.HIBERNATING); }}
            case Season.SUMMER -> setSleepState(ActivityState.AWAKE);
            case Season.SPRING -> setSleepState(ActivityState.AWAKE);
            case Season.AUTUMN -> {if(canHibernate){setSleepState(ActivityState.HIBERNATING);}}
            default -> setSleepState(ActivityState.AWAKE);
            }
        }

    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay) {
        switch (timeOfDay) {
            case TimeOfDay.DAY -> {
                if (activityType == ActivityType.DIURNAL) {
                    setSleepState(ActivityState.AWAKE);
                } else {
                    setSleepState(ActivityState.SLEEPING);
                }
            }
            case TimeOfDay.NIGHT -> {
                if (activityType == ActivityType.NOCTURNAL) {
                    setSleepState(ActivityState.AWAKE);
                } else {
                    setSleepState(ActivityState.SLEEPING);
                }
            }
        }
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

    public boolean isEdible(Organism organism) {
        return false;
    }

    public void iWasGonnaCallThisFunctionMoveButICANTBecauseSomeoneElseAlreadyTookTheNameMoveSoIAmLeftToComeUpWithAnotherNameForThisMethodWhichHasUnfortunatelyBecomeVeryLongAsAResultOfTheFactThatSomebodyHasReservedTheMoveNameSoNowImSadAndHeresALongMethodName() {
        Map.getInstance().move(this);
    }

    public void reduceHealth(float amount) {
        this.health = Math.max(0, this.health - amount);
    }

    public void restoreHealth(float amount) {
        this.health = Math.min(maxHealth, this.health + amount); 
    }
}