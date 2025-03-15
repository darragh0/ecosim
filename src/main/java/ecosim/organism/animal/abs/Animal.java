package ecosim.organism.animal.abs;


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
import ecosim.organism.plant.abs.Plant;


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

    public Animal(Size size, Diet diet, ActivityType activityType, boolean canHibernate, int num) {
        super(size, num);
        this.diet = diet;
        this.activityType = activityType;
        this.consciousState = new Conscious();
        this.survivalChance = 0.5f;
        this.reproductiveChance = 0.5f;
        this.activityState = ActivityState.SLEEPING;
        this.canHibernate = canHibernate;
    }

    public Animal(Animal animal) {
        super(animal.size, animal.name);
        this.diet = animal.diet;
        this.symbol = animal.symbol;
        this.activityType = animal.activityType;
        this.consciousState = new Conscious();
        this.survivalChance = animal.survivalChance;
        this.reproductiveChance = animal.reproductiveChance;
        this.activityState = animal.activityState;
        this.canHibernate = animal.canHibernate;
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

    public void setActivityState(ActivityState activityState) {
        this.activityState = activityState;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void makeSound() {
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
        boolean canEat = canEatAnimal(animal);

        if (canEat && Math.random() < animal.survivalChance) {
            System.out.println(this + " eats " + animal + " and gains " + animal.getNutritionalValue() + " health.");
            this.restoreHealth(animal.getNutritionalValue());
            return true;
        }
        System.out.println(this + " tries to eat " + animal + " but fails.");
        return false;
    };

    public boolean eat(Plant plant) {
        boolean canEat = canEatPlant();
        if (canEat) {
            System.out.println(this + " eats " + plant + " and gains " + plant.getNutritionalValue() + " health.");
            this.restoreHealth(plant.getNutritionalValue());
            plant.beEaten();
            return true;
        }
        return false;
    };

    public void breed() {
        if (Math.random() < this.reproductiveChance) {
            Animal child = this.createClone();
            Map.getInstance().add(child);
        }
    }

    @Override
    public abstract Animal clone();

    public Animal createClone() {
        Animal clone = clone();
        clone.setHealth(this.getMaxHealth() / 2);
        return clone;
    }

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
            case Season.WINTER -> {
                if (canHibernate) {
                    setActivityState(ActivityState.HIBERNATING);
                }
            }
            case Season.SUMMER -> setActivityState(ActivityState.AWAKE);
            case Season.SPRING -> setActivityState(ActivityState.AWAKE);
            case Season.AUTUMN -> {
                if (canHibernate) {
                    setActivityState(ActivityState.HIBERNATING);
                }
            }
            default -> setActivityState(ActivityState.AWAKE);
        }
    }

    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay) {
        switch (timeOfDay) {
            case TimeOfDay.DAY -> {
                if (activityType == ActivityType.DIURNAL) {
                    setActivityState(ActivityState.AWAKE);
                } else {
                    setActivityState(ActivityState.SLEEPING);
                }
            }
            case TimeOfDay.NIGHT -> {
                if (activityType == ActivityType.NOCTURNAL) {
                    setActivityState(ActivityState.AWAKE);
                } else {
                    setActivityState(ActivityState.SLEEPING);
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

    public boolean getCanHibernate() {
        return this.canHibernate;
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
