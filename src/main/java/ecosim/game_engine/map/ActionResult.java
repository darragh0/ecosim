package ecosim.map;

import ecosim.organism.Organism;
import ecosim.organism.animal.abs.Animal;

public class ActionResult {
    public enum ActionType {
        BASIC_ACTION,
        DIED,
        ATTEMPTED_BREEDING,
        SUCCESSFUL_BREEDING,
        ATTEMPTED_EATING,
        SUCCESSFUL_EATING
    }
    
    private final ActionType actionType;
    private final Organism actor; // Changed from Animal to Organism
    private final Organism target;
    private final int newX;
    private final int newY;
    private final Animal offspring;  // Keep this as Animal for now
    
    public ActionResult(
            ActionType actionType,
            Organism actor,  // Changed from Animal to Organism
            Organism target,
            int newX,
            int newY,
            Animal offspring) {
        this.actionType = actionType;
        this.actor = actor;
        this.target = target;
        this.newX = newX;
        this.newY = newY;
        this.offspring = offspring;
    }
    
    public ActionResult(
            ActionType actionType,
            Organism actor, // Changed from Animal to Organism
            Organism target,
            int newX,
            int newY) {
        this(actionType, actor, target, newX, newY, null);
    }
    
    // Update getter to return Organism instead of Animal
    public Organism getActor() {
        return this.actor;
    }
    
    // Keep other getters the same
    public ActionType getActionType() { return this.actionType; }
    public Organism getTarget() { return this.target; }
    public int getNewX() { return this.newX; }
    public int getNewY() { return this.newY; }
    public Animal getOffspring() { return this.offspring; }
}