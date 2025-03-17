package ecosim.game_engine.map;

/**
 * Represents 2D coordinates in the ecosystem map.
 * Stores and manages x and y positions.
 * @author darragh0
 */
public class Coords {

    private int x;
    private int y;

    /**
     * Creates coordinates with the specified position.
     * 
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    public Coords(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate.
     * 
     * @return The x-coordinate value
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate.
     * 
     * @return The y-coordinate value
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate.
     * 
     * @param x The new x-coordinate value
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate.
     * 
     * @param y The new y-coordinate value
     */
    public void setY(final int y) {
        this.y = y;
    }

}
