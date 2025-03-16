package ecosim.organism.animal.animal_state;


import ecosim.enm.ActivityState;
import ecosim.enm.ActivityType;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.map.ActionResult;
import ecosim.map.Map;
import ecosim.organism.animal.abs.Animal;


/**
 * Represents the conscious state of an animal,
 * defining its movement behavior within the ecosystem.
 * 
 * @author jjola00
 */
public class AwakeState implements AnimalState {

    @Override
    public ActionResult move(Animal animal) {

        return Map.getInstance().move(animal);
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
        return this; // Stay awake
    }

    @Override
    public AnimalState handleTimeOfDayChange(Animal animal, TimeOfDay timeOfDay) {
        ActivityType activityType = animal.getActivityType();
        
        if ((timeOfDay == TimeOfDay.DAY && activityType != ActivityType.DIURNAL) || 
            (timeOfDay == TimeOfDay.NIGHT && activityType != ActivityType.NOCTURNAL)) {
            return new SleepingState();
        }
        
        return this; // Stay awake
    }

     @Override
    public ActivityState getActivityState() {
        return ActivityState.AWAKE;
    }
}
    


