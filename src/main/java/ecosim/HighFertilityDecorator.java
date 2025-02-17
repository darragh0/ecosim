package ecosim;

public class HighFertilityDecorator extends AnimalDecorator {
    private float adjustment = 1.2f;

    public HighFertilityDecorator(Animal animal) {
        super(animal);
    }

    public float getSurvivalChance() {
        return 1.0f;
    }

    public float getReproductiveChance() {
        return adjustment;
    }
    
}
