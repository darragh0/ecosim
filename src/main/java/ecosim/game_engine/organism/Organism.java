package ecosim.game_engine.organism;


import ecosim.attrs.Movable;
import ecosim.game_engine.enm.Size;


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

    public Organism() {
    }
    public Organism(final String name) {
        this.name = name;
    }

    public abstract Organism clone();

    public Organism setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Organism setName(String name) {
        this.name = name;
        return this;
    }

    public void getName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Organism setSize(Size size) {
        this.size = size;
        this.health = (float) (this.getMaxHealth() * 0.75);
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
