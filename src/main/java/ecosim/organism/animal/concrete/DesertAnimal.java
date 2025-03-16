package ecosim.organism.animal.concrete;

import ecosim.enm.ActivityState;
import ecosim.map.ActionResult;
import ecosim.misc.SpeciesNumbering;
import ecosim.organism.animal.abs.Animal;

public class DesertAnimal extends Animal {

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
        DesertAnimal clone = new DesertAnimal();
        clone.setName(SpeciesNumbering.generateCloneName(this.getName()));
        return clone;
    }
}