package ecosim;

public class LowFertilityDecorator extends AnimalDecorator {
    private float adjustment = 0.2f;

    public LowFertilityDecorator(Animal animal) {
        super(animal);
    }

    public float getSurvivalChance() {
        return animal.getSurvivalChance();
    }

    public float getReproductiveChance() {
        return animal.getReproductiveChance() - adjustment;
    }
    
}
