package ecosim.organism.animal.decorator;

import ecosim.attrs.Observable;
import ecosim.enm.*;
import ecosim.map.ActionResult;
import ecosim.organism.Organism;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;

/**
 * Abstract base class for decorating animals using the decorator pattern with pure delegation.
 * All behavior is delegated to the wrapped animal, with specific enhancements provided by subclasses.
 * 
 * @author jjola00
 */
public abstract class AnimalDecorator extends Animal {

    protected final Animal animal;

    public AnimalDecorator(final Animal animal) {
        super(animal.getName());  // Initialize with wrapped animal’s name
        this.animal = animal;
        // Sync coordinates with the wrapped animal
        super.setCoords(animal.getX(), animal.getY());
    }

    @Override
    public String getSymbol() {
        return animal.getSymbol();
    }

    @Override
    public Animal setSymbol(String symbol) {
        animal.setSymbol(symbol);
        return this;
    }

    @Override
    public Size getSize() {
        return animal.getSize();
    }

    @Override
    public Animal setSize(Size size) {
        animal.setSize(size);
        return this;
    }

    @Override
    public float getHealth() {
        return animal.getHealth();
    }

    @Override
    public float getMaxHealth() {
        return animal.getMaxHealth();
    }

    @Override
    public String getName() {
        return animal.getName();
    }

    @Override
    public Animal setName(String name) {
        animal.setName(name);
        return this;
    }

    @Override
    public void getName(String name) {
        animal.getName(name);
    }

    @Override
    public float getNutritionalValue() {
        return animal.getNutritionalValue();
    }

    // Removed @Override for getX() and getY() since they’re final in Movable
    // Use inherited implementations from Movable instead

    @Override
    public void setCoords(int x, int y) {
        animal.setCoords(x, y);
        super.setCoords(x, y);  // Sync decorator’s coords
    }

    @Override
    public Diet getDiet() {
        return animal.getDiet();
    }

    @Override
    public Animal setDiet(Diet diet) {
        animal.setDiet(diet);
        return this;
    }

    @Override
    public ActivityType getActivityType() {
        return animal.getActivityType();
    }

    @Override
    public Animal setActivityType(ActivityType activityType) {
        animal.setActivityType(activityType);
        return this;
    }

    @Override
    public boolean canHibernate() {
        return animal.canHibernate();
    }

    @Override
    public Animal setCanHibernate(boolean canHibernate) {
        animal.setCanHibernate(canHibernate);
        return this;
    }

    @Override
    public String getSound() {
        return animal.getSound();
    }

    @Override
    public Animal setSound(String sound) {
        animal.setSound(sound);
        return this;
    }

    @Override
    public float getSurvivalChance() {
        return animal.getSurvivalChance();
    }

    @Override
    public float getReproductiveChance() {
        return animal.getReproductiveChance();
    }

    @Override
    public boolean canEatAnimal(Animal potentialPrey) {
        return animal.canEatAnimal(potentialPrey);
    }

    @Override
    public boolean canBreed(Animal potentialMate) {
        return animal.canBreed(potentialMate);
    }

    @Override
    public boolean canEatPlant() {
        return animal.canEatPlant();
    }

    @Override
    public boolean eat(Animal prey) {
        return animal.eat(prey);
    }

    @Override
    public boolean eat(Plant plant) {
        return animal.eat(plant);
    }

    @Override
    public Animal breed(Animal mate) {
        return animal.breed(mate);
    }

    @Override
    public abstract Animal clone();

    @Override
    public Animal createClone() {
        return animal.createClone();
    }

    @Override
    public ActionResult move() {
        return animal.move();
    }

    @Override
    public void update(Observable observable) {
        animal.update(observable);
    }

    @Override
    public ActivityState getActivityState() {
        return animal.getActivityState();
    }

    @Override
    public boolean isEdible(Organism organism) {
        return animal.isEdible(organism);
    }

    @Override
    public void reduceHealth(float amount) {
        animal.reduceHealth(amount);
    }

    @Override
    public void restoreHealth(float amount) {
        animal.restoreHealth(amount);
    }

    @Override
    public void setHealth(float health) {
        animal.setHealth(health);
    }
}