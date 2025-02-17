package ecosim.map;


import java.util.Optional;

import ecosim.Entity;
import ecosim.enm.Direction;
import static ecosim.util.io.ConsoleIO.prettyPrintln;


/**
 * Physical ecosystem of the given dimensions in which
 * entities (animals & plants) exist together.
 * 
 * @author darragh0
 */
public class Map {

    private static Map inst = null;
    private static final char EMPTY_CELL = '.';

    private final int width;
    private final int height;
    private final Grid grid;

    private Map(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.grid = new Grid(width, height);
    }

    public static Map init(final int width, final int height) {
        if (inst == null)
            inst = new Map(width, height);
        return inst;
    }

    public static Map getInstance() {
        if (inst == null)
            throw new IllegalStateException("Call Map.init(int, int) first");
        return inst;
    }

    private boolean inBounds(final int x, final int y) {
        return x > -1 && x < this.width && y > -1 && y < this.height;
    }

    public void addEntity(final Entity entity) {
        if (!inBounds(entity.getX(), entity.getY()))
            // TODO: Throw error?
            return;

        this.grid.add(entity);
    }

    public void moveEntity(final Entity entity, final Direction dir) {
        final int x = entity.getX() + dir.getDx();
        final int y = entity.getY() + dir.getDy();

        if (!inBounds(x, y))
            // TODO: Throw error?
            return;

        Optional<Entity> inCell = this.grid.getCell(x, y);
        if (inCell.isPresent()) {
            // TODO: Handle entity collision
        }

        this.grid.rmv(entity);
        entity.setCoords(x, y);
        this.grid.add(entity);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final String border = "─".repeat(this.width);

        sb.append("<B><b>┌")
            .append(border)
            .append("┐</b></B>\n");

        for (int y = this.height - 1; y >= 0; y--) {
            sb.append("<B><b>│</b></B>");
            for (int x = 0; x < this.width; x++) {
                final char ch = this.grid.getCell(x, y)
                    .map(Entity::getChar)
                    .orElse(EMPTY_CELL);

                sb.append(ch);
            }
            sb.append("<B><b>│</b></B>\n");
        }

        return sb.append("<B><b>└")
            .append(border)
            .append("┘</b></B>\n")
            .toString();
    }

    public void display() {
        prettyPrintln(this.toString());
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
