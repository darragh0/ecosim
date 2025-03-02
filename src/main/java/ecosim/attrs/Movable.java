package ecosim.attrs;


import ecosim.map.Coords;

/**
 * An object with *adjustable* 2D coordinates.
 * 
 * @author darragh0
 */
public abstract class Movable {

    protected final Coords coords;

    public Movable(final int x, final int y) {
        this.coords = new Coords(x, y);
    }

    public void setCoords(final int x, final int y) {
        this.coords.setX(x);
        this.coords.setY(y);
    }

    public final int getX() {
        return this.coords.getX();
    }

    public final int getY() {
        return this.coords.getY();
    }

}
