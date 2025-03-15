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
    protected Size size;

    protected float health;
    protected String name;

    public Organism(final int num) {
        this.name = "%s-%d".formatted(this.getClass().getSimpleName(), num);
    }

    public Organism(final String name) {
        this.name = name;
    }

    public abstract Organism clone();

    public Organism setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Organism setSize(Size size) {
        this.size = size;
        this.health = this.getMaxHealth() / 2;
        return this;
    }

    public Size getSize() {
        return this.size;
    }

    public String getName() {
        return name;
    }

    public float getHealth() {
        return this.health;
    }

    public float getNutritionalValue() {
        return this.size.getNutritionalValue();
    }

    public float getMaxHealth() {
        return this.size.getMaxHealth();
    }

}
