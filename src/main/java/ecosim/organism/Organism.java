package ecosim.organism;


import ecosim.attrs.Movable;
import ecosim.enm.Size;


/**
 * Physical animal or plant entity.
 * 
 * @author darragh0
 */
public abstract class Organism extends Movable {

    protected String symbol;
    protected float health;
    protected String name;
    protected Size size;

    public Organism(final Size size, final int num) {
        this.name = "%s (%d)".formatted(this.getClass().getSimpleName(), num);
        this.symbol = "E"; // can change later
        this.size = size;
        this.health = this.getMaxHealth() / 2;
    }

    public Organism(final Size size, final String name) {
        this.name = name;
        this.symbol = "E"; // can change later
        this.size = size;
        this.health = this.getMaxHealth() / 2;
    }

    public abstract Organism clone();

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public float getHealth() {
        return this.health;
    }

    public Size getSize() {
        return this.size;
    }

    public float getNutritionalValue() {
        return this.size.getNutritionalValue();
    }

    public float getMaxHealth() {
        return this.size.getMaxHealth();
    }


}
