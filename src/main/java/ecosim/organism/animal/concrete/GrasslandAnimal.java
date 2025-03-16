package ecosim.organism.animal.concrete;

import ecosim.enm.ActivityState;
import ecosim.map.ActionResult;
import ecosim.misc.SpeciesNumbering;
import ecosim.organism.animal.abs.Animal;

public class GrasslandAnimal extends Animal {

    @Override
    public ActionResult move() {
        if (this.state.getActivityState() == ActivityState.AWAKE) {
            float healthLoss = this.getMaxHealth() * 0.015f;
            this.reduceHealth(healthLoss);
        }
        
        return this.state.move(this);
    }

    @Override
    public Animal clone() {
        GrasslandAnimal clone = new GrasslandAnimal();
        clone.setName(SpeciesNumbering.generateCloneName(this.getName()));
        return clone;
    }
}