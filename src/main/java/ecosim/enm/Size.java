package ecosim.enm;


public enum Size {
    SMALL(10, 3),
    MEDIUM(20, 9),
    LARGE(30, 15);

    private final int maxHealth;
    private final int nutritionalValue;

    Size(int maxHealth, int nutritionalValue) {
        this.maxHealth = maxHealth;
        this.nutritionalValue = nutritionalValue;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getNutritionalValue() {
        return this.nutritionalValue;
    }

}
