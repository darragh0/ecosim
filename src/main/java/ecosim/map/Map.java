package ecosim.map;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    private void moveAnimalRandomly(Animal an) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        
        for (Direction dir : directions) {
            int newX = an.getX() + dir.getDx();
            int newY = an.getY() + dir.getDy();
           
            // Check if position is out of bounds
            if (outOfBounds(newX, newY)) {
                continue;
            }
            
            // Check if the position is empty (null means empty space)
            if (canMove(newX, newY) == null) {
                // Found valid empty cell, move there
                moveAnimal(an, newX, newY);
                return;
            }
        }
        // If we get here, no valid moves were found - animal stays put
    }

    public ActionResult getBreedingActionResult(final Animal an, final Organism otherOrg) {
        moveAnimalRandomly(an);
        Animal otherAnimal = (Animal) otherOrg;
    
        Animal offspring = an.breed(otherAnimal);
    
        if (offspring != null) {
            this.initialisePlacement(offspring);
    
            return new ActionResult(
                ActionResult.ActionType.SUCCESSFUL_BREEDING,
                an, otherOrg, an.getX(), an.getY(), offspring);  
        }
        
        return new ActionResult(
            ActionResult.ActionType.ATTEMPTED_BREEDING,
            an, otherOrg, an.getX(), an.getY());
    }

    public ActionResult getEatingActionResult(final Animal predator, final Organism prey, boolean wasEaten) {
        // Move the other animal to a random empty cell
        // Able to do this before the action because an action is guaranteed to happen an is not affected by position at this point
        moveAnimalRandomly(predator);
        if (wasEaten) {
            this.grid.rmv(prey);
            return new ActionResult(
                ActionResult.ActionType.SUCCESSFUL_EATING,
                predator, prey, predator.getX(), predator.getY());
        }
        
        return new ActionResult(
            ActionResult.ActionType.ATTEMPTED_EATING,
            predator, prey, predator.getX(), predator.getY());
    }
    
    public ActionResult move(final Animal an) {
        for (final Direction dir : Direction.values()) {
            int targetX = an.getX() + dir.getDx();
            int targetY = an.getY() + dir.getDy();
    
    
            Organism otherOrg = canMove(targetX, targetY);
            if (otherOrg == null) {
                continue;
            }
    
            // Handle each organism type with a single pattern match
            ActionResult result = switch (otherOrg) {
                case Animal otherAnimal -> {
                    // Check breeding first
                    if (an.canBreed(otherAnimal)) {
                        yield getBreedingActionResult(an, otherAnimal);
                    } 
                    // Then check eating
                    else if (an.canEatAnimal(otherAnimal)) {
                        boolean eaten = an.eat(otherAnimal);
                        yield getEatingActionResult(an, otherAnimal, eaten);
                    }
                
                    yield null;
                }
                case Plant plant -> {
                    // Only check eating for plants
                    if (an.canEatPlant()) {
                        boolean eaten = an.eat(plant);
                        yield getEatingActionResult(an, plant, eaten);
                    }
                    yield null;
                }
                default -> null;
            };

            // If we found a valid action, return it
            if (result != null) {
                return result;
            }
        }
        
        // No valid action found
        moveAnimalRandomly(an);
        return new ActionResult(ActionResult.ActionType.MOVED, an, null, an.getX(), an.getY());
    }
    
    private void moveAnimal(Animal an, int newX, int newY) {
        this.grid.rmv(an);
        an.setCoords(newX, newY);
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

    private Organism canMove(final int x, final int y) {
        if (!this.outOfBounds(x, y)){
            Optional<Organism> cell = this.get(x, y);
            if (cell.isPresent()) {
                return cell.get();
            } else {
                return null;
            }
        }
        return null;
    }

}
