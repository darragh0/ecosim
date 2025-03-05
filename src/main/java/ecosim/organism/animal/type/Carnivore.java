package ecosim.organism.animal.type;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.Organism;
import ecosim.organism.animal.Animal;

/**
 * Abstract class representing a carnivore,
 * defining its specific behaviors and characteristics.
 * 
 * @author jjola00
 */
public abstract class Carnivore extends Animal {

    public Carnivore(int x, int y, Size size, ActivityType activityType, boolean canHibernate) {
        super(x, y, size, Diet.CARNIVORE, activityType, canHibernate);
    }

    @Override
    public boolean isEdible(Organism organism) {
        return true; // Implement logic later
    }

    @Override
    public void eat() {
        // Implement eating behavior
    }

}
