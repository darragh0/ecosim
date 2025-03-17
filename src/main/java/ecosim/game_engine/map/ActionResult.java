package ecosim.game_engine.map;

import ecosim.game_engine.organism.Organism;
import ecosim.game_engine.organism.animal.abs.Animal;

/**
 * Represents the result of an action performed by an organism.
 * Contains information about action type, actors involved, and results.
 * @author darragh0
 */
public class ActionResult {
    /**
     * Defines the types of actions that can occur in the ecosystem.
     */
    public enum ActionType {
        BASIC_ACTION,          // Movement or idle actions
        DIED,                  // Organism death
        ATTEMPTED_BREEDING,    // Unsuccessful breeding attempt
        SUCCESSFUL_BREEDING,   // Successful breeding resulting in offspring
        ATTEMPTED_EATING,      // Unsuccessful predation attempt
        SUCCESSFUL_EATING      // Successful predation
    }
    
    private final ActionType actionType;
    private final Organism actor;
    private final Organism target;
    private final int newX;
    private final int newY;
    private final Animal offspring;
    
    /**
     * Creates a new action result with all parameters including offspring.
     * 
     * @param actionType The type of action performed
     * @param actor The organism performing the action
     * @param target The target organism of the action (if any)
     * @param newX New x-coordinate after action
     * @param newY New y-coordinate after action
     * @param offspring Any offspring resulting from the action
     */
    public ActionResult(
            ActionType actionType,
            Organism actor,
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
    
    /**
     * Creates a new action result without offspring.
     * 
     * @param actionType The type of action performed
     * @param actor The organism performing the action
     * @param target The target organism of the action (if any)
     * @param newX New x-coordinate after action
     * @param newY New y-coordinate after action
     */
    public ActionResult(
            ActionType actionType,
            Organism actor,
            Organism target,
            int newX,
            int newY) {
        this(actionType, actor, target, newX, newY, null);
    }
    
    /**
     * Gets the actor that performed the action.
     * 
     * @return The acting organism
     */
    public Organism getActor() {
        return this.actor;
    }
    
    /**
     * Gets the type of action performed.
     * 
     * @return The action type
     */
    public ActionType getActionType() { return this.actionType; }
    
    /**
     * Gets the target of the action.
     * 
     * @return The target organism
     */
    public Organism getTarget() { return this.target; }
    
    /**
     * Gets the new x-coordinate after the action.
     * 
     * @return The x-coordinate
     */
    public int getNewX() { return this.newX; }
    
    /**
     * Gets the new y-coordinate after the action.
     * 
     * @return The y-coordinate
     */
    public int getNewY() { return this.newY; }
    
    /**
     * Gets any offspring resulting from the action.
     * 
     * @return The offspring animal if breeding was successful, null otherwise
     */
    public Animal getOffspring() { return this.offspring; }
}