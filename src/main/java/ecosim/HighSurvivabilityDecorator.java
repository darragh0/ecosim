package ecosim;

class HighSurvivabilityDecorator extends AnimalDecorator {
    private float adjustment = 0.2f;

    public HighSurvivabilityDecorator(Animal animal) {
        super(animal);
    }

    public float getSurvivalChance() {
        return animal.getSurvivalChance() + adjustment;
    }

    public float getReproductiveChance() {
        return animal.getReproductiveChance();
    }
}