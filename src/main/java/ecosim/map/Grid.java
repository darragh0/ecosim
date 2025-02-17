package ecosim.map;


import java.util.Optional;

import ecosim.Entity;


/**
 * 2D grid that holds entities (animals & plants).
 *  
 * @author darragh0
 */
public class Grid {

    private final Entity[][] data;

    public Grid(final int width, final int height) {
        this.data = new Entity[height][width];
    }

    public void add(final Entity entity) {
        this.data[entity.getY()][entity.getX()] = entity;
    }

    public void rmv(final Entity entity) {
        this.data[entity.getY()][entity.getX()] = null;
    }

    public Optional<Entity> getCell(final int x, final int y) {
        return Optional.ofNullable(this.data[y][x]);
    }

}
