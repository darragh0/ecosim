package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public class HighFertilityDecorator extends AnimalDecorator {

    public HighFertilityDecorator(Animal animal) {
        super(animal);
        this.adjustment = 0.2f;
    }

    @Override
    public float getSurvivalChance() {
        return animal.getSurvivalChance() + adjustment;
    }

    @Override
    public float getReproductiveChance() {
        return animal.getReproductiveChance();
    }

}
