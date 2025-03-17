package ecosim.game_engine.organism.animal.animal_state;


import ecosim.game_engine.enm.ActivityState;
import ecosim.game_engine.enm.Season;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.map.ActionResult;
import ecosim.game_engine.organism.animal.abs.Animal;


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