package ecosim.map;


import java.util.Optional;

import ecosim.organism.Organism;


/**
 * 2D grid that holds entities (animals & plants).
 *  
 * @author darragh0
 */
public class Grid {

    private final Organism[][] data;

    public Grid(final int width, final int height) {
        this.data = new Organism[height][width];
    }

    public void add(final Organism org) {
        this.data[org.getY()][org.getX()] = org;
    }

    public void rmv(final Organism org) {
        this.data[org.getY()][org.getX()] = null;
    }

    public Optional<Organism> get(final int x, final int y) {
        return Optional.ofNullable(this.data[y][x]);
    }

}
