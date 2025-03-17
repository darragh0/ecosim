package ecosim.game_engine.map;


import java.util.Optional;

import ecosim.game_engine.organism.Organism;


/**
 * 2D grid that holds entities (animals & plants).
 * Provides an abstraction for the spatial organization of organisms.
 * @author darragh0
 */
public class Grid {

    private final Organism[][] data;

    /**x
     * Creates a new grid with the specified dimensions.
     * 
     * @param width Width of the grid
     * @param height Height of the grid
     */
    public Grid(final int width, final int height) {
        this.data = new Organism[height][width];
    }

    /**
     * Adds an organism to the grid at its current coordinates.
     * 
     * @param org The organism to add
     */
    public void add(final Organism org) {
        this.data[org.getY()][org.getX()] = org;
    }

    /**
     * Removes an organism from the grid at its current coordinates.
     * 
     * @param org The organism to remove
     */
    public void rmv(final Organism org) {
        this.data[org.getY()][org.getX()] = null;
    }

    /**
     * Gets the organism at the specified coordinates.
     * 
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return Optional containing the organism if present, empty otherwise
     */
    public Optional<Organism> get(final int x, final int y) {
        return Optional.ofNullable(this.data[y][x]);
    }

}
