package ecosim.map;


import java.util.Optional;

import static ecosim.common.Util.randInt;
import static ecosim.common.io.ConsoleIO.pprintln;
import ecosim.enm.Direction;
import ecosim.organism.Organism;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;


/**
 * Physical ecosystem of the given dimensions in which
 * organisms (animals & plants) exist together.
 * 
 * @author darragh0
 */
public class Map {

    private static Map inst = null;

    private final int width;
    private final int height;
    private final Grid grid;
    public record MapSize(int width, int height) {};

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
        this.grid.add(org);
    }

    public void initialisePlacement(final Organism org) {
        while (true) {
            final int x = randInt(0, this.width - 1);
            final int y = randInt(0, this.height - 1);

            if (this.get(x, y).isEmpty()) {
                org.setCoords(x, y);
                this.add(org);
                return;
            }
        }
    }

    public void move(final Animal an) {

        int x = an.getX();
        int y = an.getY();

        for (final Direction dir : Direction.values()) {
            x = an.getX() + dir.getDx();
            y = an.getY() + dir.getDy();

            if (this.outOfBounds(x, y))
                continue;

            Optional<Organism> cell = this.get(x, y);

            if (cell.isEmpty())
                continue;

            Organism otherOrg = cell.get();
            boolean organismEaten = switch (otherOrg) {
                case Animal otherAn -> an.eat(otherAn);
                case Plant plant -> an.eat(plant);
                default -> false;
            };

            if (organismEaten) {
                this.grid.rmv(otherOrg);
            }
            break;
        }

        this.grid.rmv(an);
        an.setCoords(x, y);
        this.grid.add(an);
    }

    public void display() {
        pprintln(this.toString());
    }

    public MapSize getMapDimensions() {
        return new MapSize(this.width, this.height);
    }

    public Grid getGrid() {
        return this.grid;
    }

    Optional<Organism> get(final int x, final int y) {
        return this.grid.get(x, y);
    }

    private boolean outOfBounds(final int x, final int y) {
        return x < 0 || x >= this.width || y < 0 || y >= this.height;
    }

}
