package ecosim.attrs;


import ecosim.map.Coords;


/**
 * An object with 2D coordinates.
 * 
 * @author darragh0
 */
public abstract class Locatable {

    protected final Coords coords;

    public Locatable(final int x, final int y) {
        this.coords = new Coords(x, y);
    }

    public final int getX() {
        return this.coords.getX();
    }

    public final int getY() {
        return this.coords.getY();
    }

}
