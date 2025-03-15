package ecosim.organism.animal.conscious_state;


import ecosim.map.ActionResult;
import ecosim.organism.animal.abs.Animal;


/**
 * Interface representing the conscious state of an animal,
 * defining the movement behavior for different states.
 * 
 * @author jjola00
 */
public interface ConsciousState {
    ActionResult move(Animal animal);

}
