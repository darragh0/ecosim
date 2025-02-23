package ecosim.organism;


public abstract class Organism {
    protected float health;
    protected float maxHealth;
    protected int x, y;
    protected int nutritionalValue;
    protected String name;

    public Organism(float maxHealth, int x, int y, int nutritionalValue) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.x = x;
        this.y = y;
        this.nutritionalValue = nutritionalValue;
        this.name = "organism";
    }

    public abstract void update();

    public float getHealth() {
        return health;
    }
    
    public String getName() {
        return name;
    }
}
