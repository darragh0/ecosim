package ecosim;

class LowSurvivabilityDecorator extends AnimalDecorator {
    private float adjustment = 0.2f;

    public LowSurvivabilityDecorator(Animal animal) {
        super(animal);
    }

    public float getSurvivalChance() {
        return animal.getSurvivalChance() - adjustment;
    }

    public float getReproductiveChance() {
        return animal.getReproductiveChance();
    }
}