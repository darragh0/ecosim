package ecosim.organism.animal;

import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

public class DesertAnimal extends Animal {
    public DesertAnimal(int x, int y, Size size, Diet diet, ActivityType activityType, boolean canHibernate) {
        super(x, y, size, diet, activityType, canHibernate);
    }
}
