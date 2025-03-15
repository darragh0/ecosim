package ecosim.organism.animal.abs;


import ecosim.enm.ActivityState;
import ecosim.map.ActionResult;


public abstract class GrasslandAnimal extends Animal {
    public GrasslandAnimal(int num) {
        super(num);
    }

    @Override
    public ActionResult move() {
        if (this.activityState == ActivityState.AWAKE) {
            float healthLoss = this.getMaxHealth() * 0.025f; // Grassland animals use less energy when moving
            this.reduceHealth(healthLoss);
            return consciousState.move(this);
        }
        return consciousState.move(this);
    }

}
