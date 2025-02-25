package ecosim.organism;


import ecosim.attrs.Locatable;


/**
 * Physical animal or plant entity.
 * 
 * @author darragh0
 */
public abstract class Organism extends Locatable {

    protected final char symbol;
    protected final int nutritionalValue;
    protected final float maxHealth;
    protected float health;
    protected String name;

    public Organism(final float maxHealth, final int x, final int y, final int nutritionalValue) {
        super(x, y);
        this.symbol = 'E'; // can change later
        this.nutritionalValue = nutritionalValue;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.name = "organism";
    }

    public abstract void update();

    public boolean isAnimal() {
        return false;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public float getNutritionalValue() {
        return this.nutritionalValue;
    }

    public float getMaxHealth() {
        return this.maxHealth;
    }

    public float getHealth() {
        return this.health;
    }

}
