package ecosim;

class LowSurvivabilityDecorator extends AnimalDecorator {
    private float adjustment = 0.8f;

    public LowSurvivabilityDecorator(Animal animal) {
        super(animal);
    }

    public float getSurvivalChance() {
        return adjustment;
    }

    public float getReproductiveChance() {
        return 1.0f;
    }
}