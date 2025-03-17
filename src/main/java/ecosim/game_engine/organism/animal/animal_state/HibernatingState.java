package ecosim.game_engine.organism.animal.animal_state;

import ecosim.game_engine.enm.ActivityState;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.map.ActionResult;
import ecosim.game_engine.organism.animal.abs.Animal;

/**
 * Represents the hibernating state of an animal,
 * defining its movement behavior within the ecosystem.
 * 
 * @author jjola00
 */
public class HibernatingState implements AnimalState {

    @Override
    public ActionResult move(Animal animal) {
        float healthRecovery = animal.getMaxHealth() * 0.03f;
        if (animal.getHealth() < animal.getMaxHealth()) {
            animal.restoreHealth(healthRecovery);
        }

        return new ActionResult(
            ActionResult.ActionType.BASIC_ACTION,
            animal,
            null,
            animal.getX(),
            animal.getY()
        );
    }


    @Override
    public AnimalState handleSeasonChange(Animal animal, Season season) {
        switch (season) {
                    case SPRING, SUMMER, AUTUMN -> {
                        if (animal.canHibernate()) {
                            return new AwakeState();
                        }
                    }
                    default -> {} // Do nothing

        }
        return this; // hibernating
    }
    @Override
    public AnimalState handleTimeOfDayChange(Animal animal, TimeOfDay timeOfDay) {
       return this; // Stay hibernating
    }

    @Override
    public ActivityState getActivityState() {
        return ActivityState.HIBERNATING;
    }
    
}