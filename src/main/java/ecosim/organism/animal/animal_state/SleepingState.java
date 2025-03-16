package ecosim.organism.animal.animal_state;

import ecosim.enm.ActivityState;
import ecosim.enm.ActivityType;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.map.ActionResult;
import ecosim.organism.animal.abs.Animal;

public class SleepingState  implements AnimalState{

    @Override
    public ActionResult move(Animal animal) {
        float healthRecovery = animal.getMaxHealth() * 0.05f;
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
