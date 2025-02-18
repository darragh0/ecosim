package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public class LowFertilityDecorator extends AnimalDecorator {

    public LowFertilityDecorator(Animal animal) {
        super(animal);
        this.reproductiveChance -= 0.2f;
    }

}
