package ecosim;

import ecosim.attrs.Observable;
import ecosim.enm.Season;

import static ecosim.enm.Season.*;

public class SeasonManager extends Observable {
    private Season currentSeason;

    public SeasonManager(ChangeManager changeManager){
        // Start the year in Autumn
        super(changeManager);
        this.currentSeason = AUTUMN;
    }

    public void getNextSeason() {
        switch (currentSeason) {
            case SPRING -> this.currentSeason = SUMMER;
            case SUMMER -> this.currentSeason = AUTUMN;
            case AUTUMN -> this.currentSeason = WINTER;
            case WINTER -> this.currentSeason = SPRING;
        };
    }

    @Override
    public Season getCurrentState() {
        return this.currentSeason;
    }

}