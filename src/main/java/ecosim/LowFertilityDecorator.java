package ecosim;

public class LowFertilityDecorator extends AnimalDecorator {
    private float adjustment = 0.8f;

    public LowFertilityDecorator(Animal animal) {
        super(animal);
    }

    public float getSurvivalChance() {
        return 1.0f;
    }

    public float getReproductiveChance() {
        return adjustment;
    }
    
}
