package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public abstract class AnimalDecorator extends Animal {

    protected Animal animal;
    protected float adjustment;

    public AnimalDecorator(Animal animal) {
        super(animal);
        this.animal = animal;
    }

    @Override
    public abstract float getSurvivalChance();

    @Override
    public abstract float getReproductiveChance();

}
