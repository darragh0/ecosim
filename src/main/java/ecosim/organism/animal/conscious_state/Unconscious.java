package ecosim.organism.animal.conscious_state;

import ecosim.organism.animal.Animal;

/**
 * Represents the unconscious state of an animal,
 * defining its movement behavior when in this state.
 * 
 * @author jjola00
 */

 public class Unconscious implements ConsciousState {
    @Override
    public String move(Animal animal) {
        float healthRecovery = animal.getMaxHealth() * 0.1f;
        if (animal.getHealth() < animal.getMaxHealth()) {
            animal.restoreHealth(healthRecovery);
        }

        switch (animal.getActivityState()) {
            case SLEEPING:
                return animal.getName() + " is currently asleep, therefore remains at " + animal.getX() + "," + animal.getY();
            case HIBERNATING:
                return animal.getName() + " is undergoing hibernation, therefore remains at " + animal.getX() + "," + animal.getY();
            default:
                return animal.getName() + " is unconscious and cannot move";
        }
    }
}