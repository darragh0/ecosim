package ecosim.game_engine.man;


import ecosim.attrs.Observable;
import ecosim.game_engine.enm.TimeOfDay;
import static ecosim.game_engine.enm.TimeOfDay.DAY;
import static ecosim.game_engine.enm.TimeOfDay.NIGHT;

/**
 * Manager class for handling time of day changes in the ecosystem.
 * Alternates between day and night, affecting organism behavior.
 * @author Kabidoye-17
 */
public class TimeOfDayMan extends Observable {

    TimeOfDay currentTimeOfDay;

    /**
     * Creates a new TimeOfDayMan instance with Night as the initial time.
     * 
     * @param changeManager The ChangeMan to use for notifying observers
     */
    public TimeOfDayMan(ChangeMan changeManager) {
        // Start time of day at night so that when time switchTimeOfDay is called, it will be day
        super(changeManager);
        this.currentTimeOfDay = NIGHT;
    }

    /**
     * Switches between day and night and notifies observers.
     * Toggles between DAY and NIGHT states.
     */
    public void switchTimeOfDay() {
        switch (currentTimeOfDay) {
            case DAY -> this.currentTimeOfDay = NIGHT;
            case NIGHT -> this.currentTimeOfDay = DAY;
        };
        this.changeManager.notifyObservers(this);
    }

    /**
     * Gets the current time of day.
     * 
     * @return The current TimeOfDay enum value
     */
    @Override
    public TimeOfDay getCurrentState() {
        return this.currentTimeOfDay;
    }

}
