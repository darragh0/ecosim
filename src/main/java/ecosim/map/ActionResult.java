package ecosim.map;

import ecosim.organism.Organism;
import ecosim.organism.animal.abs.Animal;

public class ActionResult {
    public enum ActionType {
        NONE,
        MOVED,
        ATTEMPTED_BREEDING,
        SUCCESSFUL_BREEDING,
        ATTEMPTED_EATING,
        SUCCESSFUL_EATING
    }
    
    private final ActionType actionType;
    private final Animal actor;
    private final Organism target;
    private final int newX;
    private final int newY;
    
    public ActionResult(ActionType actionType, Animal actor, Organism target, int newX, int newY) {
        this.actionType = actionType;
        this.actor = actor;
        this.target = target;
        this.newX = newX;
        this.newY = newY;
    }

    public ActionType getActionType() { return actionType; }
    public Animal getActor() { return actor; }
    public Organism getTarget() { return target; }
    public int getNewX() { return newX; }
    public int getNewY() { return newY; }
}