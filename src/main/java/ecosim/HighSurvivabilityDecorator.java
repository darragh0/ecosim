package ecosim;

class HighSurvivabilityDecorator extends AnimalDecorator {
    private float adjustment = 1.2f;

    public HighSurvivabilityDecorator(Animal animal) {
        super(animal);
    }

    public float getSurvivalChance() {
        return adjustment;
    }

    public float getReproductiveChance() {
        return 1.0f;
    }
}