package ecosim.organism.animal.concrete;

import ecosim.enm.ActivityState;
import ecosim.map.ActionResult;
import ecosim.misc.SpeciesNumbering;
import ecosim.organism.animal.abs.Animal;

public class GrasslandAnimal extends Animal {

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
        // Extract base species name
         String baseName = SpeciesNumbering.extractBaseSpeciesName(getName());
         // Get next number for this species
         int nextNumber = SpeciesNumbering.getNextNumber(baseName);
         
        GrasslandAnimal clone = new GrasslandAnimal();
        clone.setName(SpeciesNumbering.formatName(baseName, nextNumber));
        return clone;
    }
    
}