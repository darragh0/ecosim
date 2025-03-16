package ecosim.organism.animal.animal_state;

import ecosim.enm.ActivityState;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.map.ActionResult;
import ecosim.organism.animal.abs.Animal;

public class HibernatingState implements AnimalState {

    @Override
    public ActionResult move(Animal animal) {
        float healthRecovery = animal.getMaxHealth() * 0.1f;
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
