package ecosim;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.TimeOfDay;

import java.util.ArrayList;

import static ecosim.enm.TimeOfDay.DAY;
import static ecosim.enm.TimeOfDay.NIGHT;

public class TimeOfDayManager implements Observable {

    TimeOfDay currentTimeOfDay;
    ArrayList<Observer> timeOfDayObservers;

    public TimeOfDayManager(){
        // Start time of day at night so that when time switchTimeOfDay is called, it will be day
        this.currentTimeOfDay = NIGHT;
        timeOfDayObservers = new ArrayList<>();
    }

    public void  switchTimeOfDay() {
         switch (currentTimeOfDay) {
            case DAY ->  this.currentTimeOfDay = NIGHT;
            case NIGHT ->  this.currentTimeOfDay = DAY;
        };
    }

    public TimeOfDay getCurrentTimeOfDay(){
        return this.currentTimeOfDay;
    }

    @Override
    public void registerObservers(Observer observer) {
        this.timeOfDayObservers.add(observer);
    }

    @Override
    public void unregisterObservers(Observer observer) {
        this.timeOfDayObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: this.timeOfDayObservers){
            o.update(this.currentTimeOfDay);
        }
    }
}