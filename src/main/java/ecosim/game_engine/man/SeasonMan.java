package ecosim.game_engine.man;


import ecosim.attrs.Observable;
import ecosim.game_engine.enm.Season;
import static ecosim.game_engine.enm.Season.AUTUMN;
import static ecosim.game_engine.enm.Season.SPRING;
import static ecosim.game_engine.enm.Season.SUMMER;
import static ecosim.game_engine.enm.Season.WINTER;

/**
 * Manager class for handling seasonal changes in the ecosystem.
 * Tracks current season and notifies observers when seasons change.
 * @author Kabidoye-17
 */
public class SeasonMan extends Observable {
    private Season currentSeason;

    /**
     * Creates a new SeasonMan instance with Summer as the initial season.
     * 
     * @param changeManager The ChangeMan to use for notifying observers
     */
    public SeasonMan(ChangeMan changeManager) {
        // Start the year in Summer
        super(changeManager);
        this.currentSeason = SUMMER;
    }

    /**
     * Advances to the next season in the cycle and notifies observers.
     * Follows the natural progression: Summer → Autumn → Winter → Spring → Summer
     */
    public void getNextSeason() {
        switch (currentSeason) {
            case SPRING -> this.currentSeason = SUMMER;
            case SUMMER -> this.currentSeason = AUTUMN;
            case AUTUMN -> this.currentSeason = WINTER;
            case WINTER -> this.currentSeason = SPRING;
        }
        changeManager.notifyObservers(this);
    }

    /**
     * Gets the current season.
     * 
     * @return The current Season enum value
     */
    @Override
    public Season getCurrentState() {
        return this.currentSeason;
    }

}
