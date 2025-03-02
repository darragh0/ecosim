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
    public String move(Animal animal, float currentHealth, float maxHealth) {
        float adjustment = maxHealth * 0.1f;
        float updatedHealth = currentHealth - adjustment;
        Map.getInstance().move(animal);
        animal.setHealth(updatedHealth);
        return animal.getName() + " moved to coords (" + animal.getX() + "," + animal.getY() + ")";
    }
}