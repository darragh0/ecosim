package ecosim.game_engine.organism.animal.animal_state;

import ecosim.game_engine.enm.ActivityState;
import ecosim.game_engine.enm.ActivityType;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.map.ActionResult;
import ecosim.game_engine.organism.animal.abs.Animal;

/**
 * Represents the sleeping state of an animal,
 * defining its movement behavior within the ecosystem.
 * 
 * @author jjola00
 */

public class SleepingState  implements AnimalState{

    @Override
    public ActionResult move(Animal animal) {
        float healthRecovery = animal.getMaxHealth() * 0.025f;
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
                    case WINTER -> {
                        if (animal.canHibernate()) {
                            return new HibernatingState();
                        }
                    }
                    default -> {} // Do nothing

        }
        return this; // Stay asleep
    }

     @Override
    public AnimalState handleTimeOfDayChange(Animal animal, TimeOfDay timeOfDay) {
        ActivityType activityType = animal.getActivityType();
        
        if ((timeOfDay == TimeOfDay.DAY && activityType == ActivityType.DIURNAL) || 
            (timeOfDay == TimeOfDay.NIGHT && activityType == ActivityType.NOCTURNAL)) {
            return new AwakeState();
        }
        
        return this; // Stay asleep
    }


    @Override
    public ActivityState getActivityState() {
        return ActivityState.SLEEPING;
    }
    
}