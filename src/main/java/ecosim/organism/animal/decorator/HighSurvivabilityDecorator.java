package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public class HighSurvivabilityDecorator extends AnimalDecorator {

    public HighSurvivabilityDecorator(Animal animal) {
        super(animal);
        this.adjustment = 0.2f;
    }

    @Override
    public float getSurvivalChance() {
        return this.animal.getSurvivalChance() + this.adjustment;
    }

    @Override
    public float getReproductiveChance() {
        return this.animal.getReproductiveChance();
    }

}
