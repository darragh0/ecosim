package ecosim.game_engine.organism.animal.concrete;

import ecosim.game_engine.enm.ActivityState;
import ecosim.game_engine.map.ActionResult;
import ecosim.game_engine.organism.animal.abs.Animal;

public class DesertAnimal extends Animal {
    
    public DesertAnimal() {
            super();
    }

    public DesertAnimal(DesertAnimal desertAnimal) {
            super(desertAnimal);
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
            return new DesertAnimal(this);
    }
}