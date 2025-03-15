package ecosim.organism.animal.abs;


import ecosim.enm.ActivityState;
import ecosim.map.ActionResult;


public abstract class DesertAnimal extends Animal {
    public DesertAnimal(int num) {
        super(num);
    }

    @Override
    public ActionResult move() {
        if (this.activityState == ActivityState.AWAKE) {
            float healthLoss = this.getMaxHealth() * 0.05f; // Desert animals use more energy when moving
            this.reduceHealth(healthLoss);
        }
        return consciousState.move(this);
    }

}
