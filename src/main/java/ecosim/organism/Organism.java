package ecosim.organism;


import ecosim.attrs.Movable;
import ecosim.enm.Size;


/**
 * Physical animal or plant entity.
 * 
 * @author darragh0
 */
public abstract class Organism extends Movable {

    protected final char symbol;
    protected final int nutritionalValue;
    protected final float maxHealth;
    protected float health;
    protected String name;
    protected Size size;

    public Organism(final Size size, final float maxHealth, final int x, final int y, final int nutritionalValue) {
        super(x, y);
        this.symbol = 'E'; // can change later
        this.nutritionalValue = nutritionalValue;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.name = "organism";
        this.size = size;
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
