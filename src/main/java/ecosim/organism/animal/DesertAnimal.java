package ecosim.organism.animal;

import ecosim.enm.ActivityState;
import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

public abstract class DesertAnimal extends Animal {
    public DesertAnimal(Size size, Diet diet, ActivityType activityType, boolean canHibernate) {
        super(size, diet, activityType, canHibernate);
    }
    @Override
    public String move() {
        if (this.activityState == ActivityState.AWAKE) {
            float healthLoss = this.getMaxHealth() * 0.05f; //Desert animals use more energy when moving
            this.reduceHealth(healthLoss); 
            return consciousState.move(this);
        }
        return consciousState.move(this);
    }
}
