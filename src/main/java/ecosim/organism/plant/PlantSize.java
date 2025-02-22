package ecosim.organism.plant;


public enum PlantSize {
    SMALL(1, 7, 3),
    MEDIUM(2, 14, 6),
    LARGE(3, 21, 9);

    private final int biteCapacity;
    private final int maxHealth;
    private final int nutritionalValue;

    PlantSize(int biteCapacity, int maxHealth, int nutritionalValue) {
        this.biteCapacity = biteCapacity;
        this.maxHealth = maxHealth;
        this.nutritionalValue = nutritionalValue;
    }

    public int getBiteCapacity() {
        return biteCapacity;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

}




