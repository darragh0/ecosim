package ecosim;


import ecosim.enm.Direction;
import ecosim.map.Coords;
import ecosim.map.Map;


/**
 * Physical animal or plant entity.
 * 
 * @author darragh0
 */
public class Entity {

    private final char symbol;
    private final Coords coords;

    public Entity(final char ch, final int x, final int y) {
        this.symbol = ch;
        this.coords = new Coords(x, y);
    }

    public void move(final Direction dir) {
        Map.getInstance().moveEntity(this, dir);
    }

    public void setCoords(final int x, final int y) {
        this.coords.setX(x);
        this.coords.setY(y);
    }

    public char getChar() {
        return this.symbol;
    }

    public int getX() {
        return this.coords.getX();
    }

    public int getY() {
        return this.coords.getY();
    }

}
