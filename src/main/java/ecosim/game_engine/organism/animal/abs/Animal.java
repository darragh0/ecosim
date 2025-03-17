package ecosim.game_engine.organism.animal.abs;


import ecosim.attrs.Observable;
import static ecosim.common.Util.randFloat;

import ecosim.attrs.Observable;
import ecosim.game_engine.enm.ActivityState;
import ecosim.game_engine.enm.ActivityType;
import ecosim.game_engine.enm.Diet;
import ecosim.game_engine.enm.Event;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.Size;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.map.ActionResult;
import ecosim.game_engine.misc.Observer;
import ecosim.game_engine.misc.SpeciesNumbering;
import ecosim.game_engine.organism.Organism;
import ecosim.game_engine.organism.animal.animal_state.AnimalState;
import ecosim.game_engine.organism.animal.animal_state.AwakeState;
import ecosim.game_engine.organism.plant.abs.Plant;


/**
 * Abstract class representing an animal in the ecosystem,
 * defining its properties, behaviors, and interactions.
 * 
 * @author jjola00
 */
public abstract class Animal extends Organism{


    protected Diet diet;
    protected ActivityType activityType;
    protected boolean canHibernate;
    protected String sound;

    protected float survivalChance;
    protected float reproductiveChance;
    protected AnimalState state;

    public Animal() {
        this.state = new AwakeState();
        this.survivalChance = 0.5f;
        this.reproductiveChance = 0.5f;
    }

    /**
     * Copy constructor for Animal class to support the prototype pattern.
     * Creates a new Animal with the same properties as the original.
     * 
     * @param source The Animal instance to copy from
     */
    public Animal(Animal source) {
        // Copy Organism properties
        this.symbol = source.symbol;
        this.size = source.size;
        this.name = SpeciesNumbering.generateCloneName(source.name);
        this.health = (float) (source.getMaxHealth() * 0.75); // New animals start at 75% health

        // Copy Animal-specific properties
        this.diet = source.diet;
        this.activityType = source.activityType;
        this.canHibernate = source.canHibernate;
        this.sound = source.sound;
        this.survivalChance = source.survivalChance;
        this.reproductiveChance = source.reproductiveChance;
        this.state = new AwakeState(); // New animals always start in the awake state
    }


    @Override
    public Animal setSymbol(String symbol) {
        super.setSymbol(symbol);
        return this;
    }

    @Override
    public Animal setSize(Size size) {
        super.setSize(size);
        return this;
    }

    public Animal setDiet(Diet diet) {
        this.diet = diet;
        return this;
    }

    public Diet getDiet() {
        return this.diet;
    }

    public Animal setActivityType(ActivityType activityType) {
        this.activityType = activityType;
        return this;
    }

    public ActivityType getActivityType() {
        return this.activityType;
    }

    public Animal setCanHibernate(boolean canHibernate) {
        this.canHibernate = canHibernate;
        return this;
    }

    public boolean canHibernate() {
        return this.canHibernate;
    }

    public Animal setSound(String sound) {
        this.sound = sound;
        return this;
    }

    @Override
    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public String getSound() {
        return this.sound;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getSurvivalChance() {
        return this.survivalChance;
    }

    public float getReproductiveChance() {
        return this.reproductiveChance;
    }

    public boolean canEatAnimal(Animal potentialPrey) {
        boolean isDietCompatible = this.diet != Diet.HERBIVORE;
        boolean isDifferentSpecies = !this.canBreed(potentialPrey);
        boolean isPredatorLargeEnough = this.size.ordinal() >= potentialPrey.getSize().ordinal();

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
        if (randFloat(0.0f, 1.0f) < animal.survivalChance) {
            this.restoreHealth(animal.getNutritionalValue());
            return true;
        }
        return false;
    };

    public boolean eat(Plant plant) {
        this.restoreHealth(plant.getNutritionalValue());
        plant.beEaten();
        return true; // Eating was successful, even if plant isn't dead
    }

    public Animal breed(Animal mate) {
        float combinedReproductiveChance = this.reproductiveChance * mate.reproductiveChance;

        if (randFloat(0.0f, 1.0f) < combinedReproductiveChance)
            return this.clone();
        return null;
    }

    @Override
    public abstract Animal clone();

    public ActionResult move() {
        // Delegate to the current state
        return this.state.move(this);
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
        AnimalState newState = this.state.handleSeasonChange(this, season);
        if (newState != this.state) {
            this.state = newState;
        }
    }

    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay) {
        AnimalState newState = this.state.handleTimeOfDayChange(this, timeOfDay);
        if (newState != this.state) {
            this.state = newState;
        }
    }

    public ActivityState getActivityState() {
        return this.state.getActivityState();
    }

    public boolean isEdible(Organism organism) {
        return false;
    }

}
