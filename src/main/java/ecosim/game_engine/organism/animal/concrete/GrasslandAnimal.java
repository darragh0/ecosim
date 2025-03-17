package ecosim.organism.animal.concrete;

import ecosim.enm.ActivityState;
import ecosim.map.ActionResult;
import ecosim.organism.animal.abs.Animal;

public class GrasslandAnimal extends Animal {

    public GrasslandAnimal() {
        super();
    }

    public GrasslandAnimal(GrasslandAnimal grasslandAnimal) {
        super(grasslandAnimal);
    }
    
    @Override
    public ActionResult move() {
        if (this.state.getActivityState() == ActivityState.AWAKE) {
            float healthLoss = this.getMaxHealth() * 0.025f;
            this.reduceHealth(healthLoss);
        }
        
        return this.state.move(this);
    }

    @Override
    public Animal clone() {
        return new GrasslandAnimal(this);
    }
    
}