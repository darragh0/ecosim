package ecosim.map;


import java.util.Optional;

import ecosim.enm.Direction;
import ecosim.organism.Organism;
import ecosim.organism.animal.Animal;
import static ecosim.util.io.ConsoleIO.prettyPrintln;
import ecosim.util.io.enm.BoxDrawingChar;


/**
 * Physical ecosystem of the given dimensions in which
 * organisms (animals & plants) exist together.
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

    public void add(final Organism org) {
        if (!inBounds(org.getX(), org.getY()))
            // TODO: Throw error?
            return;

        this.grid.add(org);
    }

    public void move(final Animal an) {

        int x = an.getX();
        int y = an.getY();

        for (final Direction dir : Direction.values()) {
            x = an.getX() + dir.getDx();
            y = an.getY() + dir.getDy();

            if (!inBounds(x, y))
                continue;

            Optional<Organism> cell = this.grid.getCell(x, y);

            if (cell.isEmpty())
                continue;

            Organism otherOrg = cell.get();
            if (otherOrg instanceof Animal otherAn) {
                if (an.getSize().getNutritionalValue() < otherAn.getSize().getNutritionalValue())
                    continue;
            }

            an.eat(); // TODO: Replace with working eat method
            this.grid.rmv(otherOrg);
            break;
        }

        this.grid.rmv(an);
        an.setCoords(x, y);
        this.grid.add(an);
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

    private boolean inBounds(final int x, final int y) {
        return x > -1 && x < this.width && y > -1 && y < this.height;
    }

}
