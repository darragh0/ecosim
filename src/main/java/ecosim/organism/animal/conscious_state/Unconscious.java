package ecosim.organism.animal.conscious_state;


import ecosim.map.ActionResult;
import ecosim.organism.animal.abs.Animal;


/**
 * Represents the unconscious state of an animal,
 * defining its movement behavior when in this state.
 * 
 * @author jjola00
 */

public class Unconscious implements ConsciousState {
    @Override
    public ActionResult move(Animal animal) {
        float healthRecovery = animal.getMaxHealth() * 0.1f;
        if (animal.getHealth() < animal.getMaxHealth()) {
            animal.restoreHealth(healthRecovery);
        }

        return new ActionResult(
            ActionResult.ActionType.NONE,
            animal,
            null,
            animal.getX(),
            animal.getY()
        );
        // switch (animal.getActivityState()) {
        //     case SLEEPING:
        //         return animal.getName() + " is currently asleep, therefore remains at " + animal.getX() + ","
        //             + animal.getY();
        //     case HIBERNATING:
        //         return animal.getName() + " is undergoing hibernation, therefore remains at " + animal.getX() + ","
        //             + animal.getY();
        //     default:
        //         return animal.getName() + " is unconscious and cannot move";
        // }
    }

}
