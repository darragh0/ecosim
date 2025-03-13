package ecosim.man;


import ecosim.attrs.Observable;
import ecosim.enm.Season;
import static ecosim.enm.Season.AUTUMN;
import static ecosim.enm.Season.SPRING;
import static ecosim.enm.Season.SUMMER;
import static ecosim.enm.Season.WINTER;


public class SeasonMan extends Observable {
    private Season currentSeason;

    public SeasonMan(ChangeMan changeManager) {
        // Start the year in Autumn
        super(changeManager);
        this.currentSeason = SUMMER;
    }

    public void getNextSeason() {
        switch (currentSeason) {
            case SPRING -> this.currentSeason = SUMMER;
            case SUMMER -> this.currentSeason = AUTUMN;
            case AUTUMN -> this.currentSeason = WINTER;
            case WINTER -> this.currentSeason = SPRING;
        }
        changeManager.notifyObservers(this);
    }

    @Override
    public Season getCurrentState() {
        return this.currentSeason;
    }

}
