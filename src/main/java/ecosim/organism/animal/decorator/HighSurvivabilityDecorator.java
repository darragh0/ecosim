package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public class HighSurvivabilityDecorator extends AnimalDecorator {

    public HighSurvivabilityDecorator(Animal animal) {
        super(animal);
        this.survivalChance += 0.2f;
    }

}
