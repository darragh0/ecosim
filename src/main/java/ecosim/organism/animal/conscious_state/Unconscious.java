package ecosim.organism.animal.conscious_state;

import ecosim.organism.animal.Animal;

public class Unconscious implements ConsciousState {
    @Override
    public String move(Animal animal) {
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