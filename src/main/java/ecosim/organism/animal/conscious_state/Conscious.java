package ecosim.organism.animal.conscious_state;

import ecosim.organism.animal.Animal;
import ecosim.map.Map;

/**
 * Represents the conscious state of an animal,
 * defining its movement behavior within the ecosystem.
 * 
 * @author jjola00
 */
public class Conscious implements ConsciousState {
    @Override
    public String move(Animal animal) {
        Map.getInstance().move(animal);
        return animal.getName() + " moved to coords (" + animal.getX() + "," + animal.getY() + ")";
    }
}