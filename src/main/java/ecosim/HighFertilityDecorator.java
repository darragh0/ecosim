package ecosim;

public class HighFertilityDecorator extends AnimalDecorator {
    private float adjustment = 0.2f;

    public HighFertilityDecorator(Animal animal) {
        super(animal);
    }

    public float getSurvivalChance() {
        return animal.getSurvivalChance() + adjustment;
    }

    public float getReproductiveChance() {
        return animal.getReproductiveChance();
    }
    
}
