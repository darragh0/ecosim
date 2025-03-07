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

    public Organism(final Size size) {
        this.symbol = 'E'; // can change later
        this.size = size;
        this.nutritionalValue = this.size.getNutritionalValue();
        this.maxHealth = this.size.getMaxHealth();
        this.health = this.maxHealth / 2;
        this.name = "organism";
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
