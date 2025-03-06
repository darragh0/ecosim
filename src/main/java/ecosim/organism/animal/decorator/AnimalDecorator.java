package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;

/**
 * Abstract class for decorating animals, allowing for
 * additional behaviors or properties to be added.
 * 
 * @author jjola00
 */
public abstract class AnimalDecorator extends Animal {

    protected final Animal animal;
    protected float adjustment;

    public AnimalDecorator(final Animal animal) {
        super(animal);
        this.animal = animal;
    }
    @Override
    public String move() {
        return this.move();
    }
}
