package ecosim.organism.animal.animal_state;


import ecosim.enm.ActivityState;
import ecosim.enm.Season;
import ecosim.enm.TimeOfDay;
import ecosim.map.ActionResult;
import ecosim.organism.animal.abs.Animal;


/**
 * Interface representing the conscious state of an animal,
 * defining the movement behavior for different states.
 * 
 * @author jjola00
 */

public interface AnimalState {
   
    ActionResult move(Animal animal);
    
    AnimalState handleSeasonChange(Animal animal, Season season);
    
    AnimalState handleTimeOfDayChange(Animal animal, TimeOfDay timeOfDay);

    ActivityState getActivityState();
}
