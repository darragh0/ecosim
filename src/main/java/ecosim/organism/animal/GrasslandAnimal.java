package ecosim.organism.animal;

import ecosim.enm.ActivityState;
import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

public abstract class GrasslandAnimal extends Animal {
    public GrasslandAnimal(Size size, Diet diet, ActivityType activityType, boolean canHibernate, String name) {
        super(size, diet, activityType, canHibernate, name);
    }
    @Override
    public String move() {
        if (this.activityState == ActivityState.AWAKE) {
            float healthLoss = this.getMaxHealth() * 0.025f; //Grassland animals use less energy when moving
            this.reduceHealth(healthLoss); 
            return consciousState.move(this);
        }
        return consciousState.move(this);
    }
}
