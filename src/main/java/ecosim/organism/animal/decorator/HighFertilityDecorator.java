package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public class HighFertilityDecorator extends AnimalDecorator {

    public HighFertilityDecorator(Animal animal) {
        super(animal);
        this.reproductiveChance += 0.2f;
    }

}
