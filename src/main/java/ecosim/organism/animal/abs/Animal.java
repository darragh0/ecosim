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
import ecosim.map.ActionResult;
import ecosim.organism.Organism;
import ecosim.organism.animal.conscious_state.Conscious;
import ecosim.organism.animal.conscious_state.ConsciousState;
import ecosim.organism.animal.conscious_state.Unconscious;
import ecosim.organism.plant.abs.Plant;


/**
 * Abstract class representing an animal in the ecosystem,
 * defining its properties, behaviors, and interactions.
 * 
 * @author jjola00
 */
public abstract class Animal extends Organism implements Observer {

    private final ConsciousState CONSCIOUS_STATE = new Conscious();
    private final ConsciousState UNCONSCIOUS_STATE = new Unconscious();

    protected Diet diet;
    protected ActivityType activityType;
    protected ActivityState activityState;
    protected ConsciousState consciousState;
    protected boolean canHibernate;
    protected float survivalChance;
    protected float reproductiveChance;

    public Animal(Size size, Diet diet, ActivityType activityType, boolean canHibernate, int num) {
        super(size, num); // Default coordinates (0, 0)
        this.diet = diet;
        this.activityType = activityType;
        this.activityState = ActivityState.SLEEPING;
        this.consciousState = new Conscious();
        this.survivalChance = 0.5f;
        this.reproductiveChance = 0.5f;
    }

    public Animal(Animal animal) {
        super(animal.size, animal.name);
        this.diet = animal.diet;
        this.symbol = animal.symbol;
        this.activityType = animal.activityType;
        this.activityState = animal.activityState;
        this.consciousState = new Conscious();
        this.survivalChance = animal.survivalChance;
        this.reproductiveChance = animal.reproductiveChance;
        this.canHibernate = animal.canHibernate;
    }

    public Diet getDiet() {
        return this.diet;
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

    public ConsciousState getConsciousState() {
        return this.consciousState;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public String getSound() {
        return "Animal sound";
    };

    public float getSurvivalChance() {
        return this.survivalChance;
    }

    public float getReproductiveChance() {
        return this.reproductiveChance;
    }

    public boolean canEatAnimal(Animal potentialPrey) {
        boolean isDietCompatible = this.diet != Diet.HERBIVORE;
        boolean isDifferentSpecies = !this.canBreed(potentialPrey);
        boolean isPredatorLargeEnough = this.size.ordinal() >= potentialPrey.size.ordinal();

        return isDietCompatible && isDifferentSpecies && isPredatorLargeEnough;
    }

    public boolean canBreed(Animal potentialMate) {
        // Extract the base type from both animal names
        String thisBaseType = extractBaseType(this.getName());
        String mateBaseType = extractBaseType(potentialMate.getName());

        // Compare the base types
        return thisBaseType.equals(mateBaseType);
    }

    // Helper method to extract the base animal type from the name
    private String extractBaseType(String name) {
        // Extract the base name (e.g., "Lion" from "Lion (1)")
        int parenthesisIndex = name.indexOf('(');
        if (parenthesisIndex > 0) {
            return name.substring(0, parenthesisIndex).trim();
        }
        return name.trim();
    }


    public boolean canEatPlant() {
        return this.diet != Diet.CARNIVORE;
    }

    public boolean eat(Animal animal) {
        if (Math.random() < animal.survivalChance) {
            this.restoreHealth(animal.getNutritionalValue());
            return true;
        }
        return false;
    };

    public boolean eat(Plant plant) {
        this.restoreHealth(plant.getNutritionalValue());
        plant.beEaten();
        return true;
    };

    public Animal breed(Animal mate) {
    
        if (Math.random() < this.reproductiveChance) {
            Animal child = mate.createClone();
            return child;
        }
        return null;
    }

    @Override
    public abstract Animal clone();

    public Animal createClone() {
        Animal clone = clone();
        clone.setHealth(this.getMaxHealth() / 2);
        return clone;
    }

    public ActionResult move() {
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
                    this.consciousState = UNCONSCIOUS_STATE;
                }
            }
            case Season.SUMMER, Season.SPRING -> {
                setActivityState(ActivityState.AWAKE);
                this.consciousState = CONSCIOUS_STATE;
            }
            case Season.AUTUMN -> {
                if (canHibernate) {
                    setActivityState(ActivityState.HIBERNATING);
                    this.consciousState = UNCONSCIOUS_STATE;
                }
            }
            default -> {
                setActivityState(ActivityState.AWAKE);
                this.consciousState = CONSCIOUS_STATE;
            }
        }
    }

    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay) {
        switch (timeOfDay) {
            case TimeOfDay.DAY -> {
                if (activityType == ActivityType.DIURNAL) {
                    setActivityState(ActivityState.AWAKE);
                    this.consciousState = CONSCIOUS_STATE;
                } else {
                    setActivityState(ActivityState.SLEEPING);
                    this.consciousState = UNCONSCIOUS_STATE;
                }
            }
            case TimeOfDay.NIGHT -> {
                if (activityType == ActivityType.NOCTURNAL) {
                    setActivityState(ActivityState.AWAKE);
                    this.consciousState = CONSCIOUS_STATE;
                } else {
                    setActivityState(ActivityState.SLEEPING);
                    this.consciousState = UNCONSCIOUS_STATE;
                }
            }
        }
    }

    public boolean isEdible(Organism organism) {
        return false;
    }

    public void reduceHealth(float amount) {
        this.health = Math.max(0, this.health - amount);
    }

    public void restoreHealth(float amount) {
        this.health = Math.min(this.getMaxHealth(), this.health + amount);
    }

}
