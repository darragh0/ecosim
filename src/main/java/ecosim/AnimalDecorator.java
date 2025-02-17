package ecosim;

public abstract class AnimalDecorator extends Animal {
    protected Animal animal;

    public AnimalDecorator(Animal animal) {
        super(animal.maxHealth, animal.x, animal.y, animal.nutritionalValue, 
              animal.size, animal.diet, animal.activityType, animal.canHibernate);
        this.animal = animal;
    }
    public abstract float getSurvivalChance();
    public abstract float getReproductiveChance();
}