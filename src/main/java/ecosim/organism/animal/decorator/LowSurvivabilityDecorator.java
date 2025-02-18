package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public class LowSurvivabilityDecorator extends AnimalDecorator {

    public LowSurvivabilityDecorator(Animal animal) {
        super(animal);
        this.survivalChance -= 0.2f;
    }

}
