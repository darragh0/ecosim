package ecosim.man;


import ecosim.attrs.Observable;
import ecosim.enm.TimeOfDay;
import static ecosim.enm.TimeOfDay.DAY;
import static ecosim.enm.TimeOfDay.NIGHT;


public class TimeOfDayMan extends Observable {

    TimeOfDay currentTimeOfDay;

    public TimeOfDayMan(ChangeMan changeManager) {
        // Start time of day at night so that when time switchTimeOfDay is called, it will be day
        super(changeManager);
        this.currentTimeOfDay = NIGHT;
    }

    public void switchTimeOfDay() {
        switch (currentTimeOfDay) {
            case DAY -> this.currentTimeOfDay = NIGHT;
            case NIGHT -> this.currentTimeOfDay = DAY;
        };
        this.changeManager.notifyObservers(this);
    }

    @Override
    public TimeOfDay getCurrentState() {
        return this.currentTimeOfDay;
    }

}
