package ecosim.enm;


public enum Size {
    SMALL(10, 5),
    MEDIUM(20, 10),
    LARGE(30, 15);

    private final int maxHealth;
    private final int nutritionalValue;

    Size(int maxHealth, int nutritionalValue) {
        this.maxHealth = maxHealth;
        this.nutritionalValue = nutritionalValue;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

}
