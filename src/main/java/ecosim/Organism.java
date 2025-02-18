package ecosim;

public abstract class Organism {
    protected float health;
    protected float maxHealth;
    protected int x, y;
    protected int nutritionalValue;

    public Organism(float maxHealth, int x, int y, int nutritionalValue) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.x = x;
        this.y = y;
        this.nutritionalValue = nutritionalValue;
    }

    public abstract void update();
}
