package ecosim.organism.animal;

import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

public abstract class GrasslandAnimal extends Animal {
    public GrasslandAnimal(int x, int y, Size size, Diet diet, ActivityType activityType, boolean canHibernate) {
        super(x, y, size, diet, activityType, canHibernate);
    }
}
