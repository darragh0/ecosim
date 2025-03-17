package ecosim.game_engine.organism.animal.animal_state;


import ecosim.game_engine.enm.ActivityState;
import ecosim.game_engine.enm.ActivityType;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.map.ActionResult;
import ecosim.game_engine.map.Map;
import ecosim.game_engine.organism.animal.abs.Animal;


/**
 * Represents the conscious state of an animal,
 * defining its movement behavior within the ecosystem.
 * 
 * @author jjola00
 */
public class AwakeState implements AnimalState {

    @Override
public ActionResult move(Animal animal) {
    
    // Reduce health by 35% of max health when moving
    float healthReduction = animal.getMaxHealth() * 0.035f;
    animal.reduceHealth(healthReduction);
    
    // Get movement result from map
    ActionResult result = Map.getInstance().move(animal);
    
    return result;
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
    

