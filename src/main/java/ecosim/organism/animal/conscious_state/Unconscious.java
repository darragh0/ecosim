package ecosim.organism.animal.conscious_state;

import ecosim.organism.animal.Animal;

public class Unconscious implements ConsciousState {
    @Override
    public String move(Animal animal) {
        return animal.getName() + " is unconscious, therefore remains at " + animal.getX() + "," + animal.getY();
    }
}
