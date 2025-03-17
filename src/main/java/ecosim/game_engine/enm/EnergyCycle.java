package ecosim.game_engine.enm;
/**
 * Enum representing the different energy cycles an organism can have.
 * @author MiaBorkoo 
 */

import static ecosim.common.Util.title;


public enum EnergyCycle {
    PHOTOSYNTHESIS,
    RESPIRATION;

    @Override
    public String toString() {
        return title(this.name());
    }

}

