package ecosim.organism.animal.conscious_state;


import ecosim.map.Map;
import ecosim.organism.animal.abs.Animal;


/**
 * Represents the conscious state of an animal,
 * defining its movement behavior within the ecosystem.
 * 
 * @author jjola00
 */
public class Conscious implements ConsciousState {
    @Override
    public String move(Animal animal) {
        float healthLoss = animal.getMaxHealth() * 0.1f;
        animal.reduceHealth(healthLoss);

        Map.getInstance().move(animal);

        return animal.getName() + " moved to coords (" + animal.getX() + "," + animal.getY() + ")";
    }

}
