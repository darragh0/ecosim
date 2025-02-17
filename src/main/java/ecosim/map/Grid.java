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

    public Grid(int width, int height) {
        this.data = new Entity[height][width];
    }

    public void add(Entity entity) {
        this.data[entity.getY()][entity.getX()] = entity;
    }

    public void rmv(Entity entity) {
        this.data[entity.getY()][entity.getX()] = null;
    }

    public Optional<Entity> getCell(int x, int y) {
        return Optional.ofNullable(this.data[y][x]);
    }

}
