package ecosim.game_engine.enm;

import static ecosim.common.Util.title;

/**
 * Enum representing the various activity states
 * an organism can be in, such as sleeping or hibernating.
 * 
 * @author jjola00
 */
public enum ActivityState {
    SLEEPING,
    HIBERNATING,
    AWAKE;

    @Override
    public String toString() {
        return title(this.name());
    }
}
