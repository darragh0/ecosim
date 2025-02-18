package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public abstract class AnimalDecorator extends Animal {

    protected final Animal animal;
    protected float adjustment;

    public AnimalDecorator(final Animal animal) {
        super(animal);
        this.animal = animal;
    }

}
