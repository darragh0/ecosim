package ecosim.organism.animal.decorator;


import ecosim.organism.animal.Animal;


public class LowFertilityDecorator extends AnimalDecorator {

    public LowFertilityDecorator(Animal animal) {
        super(animal);
        this.adjustment = 0.2f;
    }

    @Override
    public float getSurvivalChance() {
        return this.animal.getSurvivalChance();
    }

    @Override
    public float getReproductiveChance() {
        return this.animal.getReproductiveChance() - this.adjustment;
    }

}
