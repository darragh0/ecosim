package ecosim;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.Season;

import java.util.ArrayList;

import static ecosim.enm.Season.*;

public class SeasonManager implements Observable{
    private Season currentSeason;
    ArrayList<Observer> seasonObservers;

    public SeasonManager(){
        // Start the year in Autumn
        this.currentSeason = AUTUMN;
        this.seasonObservers = new ArrayList<>();
    }

    public void getNextSeason() {
        switch (currentSeason) {
            case SPRING -> this.currentSeason = SUMMER;
            case SUMMER -> this.currentSeason = AUTUMN;
            case AUTUMN -> this.currentSeason = WINTER;
            case WINTER -> this.currentSeason = SPRING;
        };
    }

    public Season getCurrentSeason() {
        return this.currentSeason;
    }

    @Override
    public void registerObservers(Observer observer) {
        this.seasonObservers.add(observer);
    }

    @Override
    public void unregisterObservers(Observer observer) {
        this.seasonObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : this.seasonObservers){
            o.update(currentSeason);
        }
    }
}