package ecosim.organism.animal.abs;


import ecosim.enm.ActivityState;
import ecosim.map.ActionResult;


public abstract class DesertAnimal extends Animal {
    public DesertAnimal(int num) {
        super(num);
    }

    @Override
    public ActionResult move() {

        if (this.state.getActivityState() == ActivityState.AWAKE ) {
            float healthLoss = this.getMaxHealth() * 0.025f; // Grassland animals use less energy when moving
            this.reduceHealth(healthLoss);
        }
        
        return this.state.move(this);
    }

}