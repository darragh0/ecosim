package ecosim.map;


import java.util.Optional;

import ecosim.enm.Direction;
import ecosim.organism.Organism;
import static ecosim.util.io.ConsoleIO.prettyPrintln;
import ecosim.util.io.enm.BoxDrawingChar;


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

    public void addEntity(final Organism org) {
        if (!inBounds(org.getX(), org.getY()))
            // TODO: Throw error?
            return;

        this.grid.add(org);
    }

    public void moveEntity(final Organism org, final Direction dir) {
        final int x = org.getX() + dir.getDx();
        final int y = org.getY() + dir.getDy();

        if (!inBounds(x, y))
            // TODO: Throw error?
            return;

        Optional<Organism> inCell = this.grid.getCell(x, y);
        if (inCell.isPresent()) {
            // TODO: Handle entity collision
        }

        this.grid.rmv(org);
        org.setCoords(x, y);
        this.grid.add(org);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final String border = BoxDrawingChar.HORIZONTAL.repeat(this.width);

        sb.append("<B><b>")
            .append(BoxDrawingChar.TOP_LEFT.getValue())
            .append(border)
            .append(BoxDrawingChar.TOP_RIGHT.getValue())
            .append("</b></B>\n");

        for (int y = this.height - 1; y >= 0; y--) {
            sb.append("<B><b>")
                .append(BoxDrawingChar.VERTICAL.getValue())
                .append("</b></B>");

            for (int x = 0; x < this.width; x++) {
                final char ch = this.grid.getCell(x, y)
                    .map(Organism::getSymbol)
                    .orElse(EMPTY_CELL);

                sb.append(ch);
            }

            sb.append("<B><b>")
                .append(BoxDrawingChar.VERTICAL.getValue())
                .append("</b></B>\n");
        }

        return sb.append("<B><b>")
            .append(BoxDrawingChar.BOTTOM_LEFT.getValue())
            .append(border)
            .append(BoxDrawingChar.BOTTOM_RIGHT.getValue())
            .append("</b></B>\n")
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
