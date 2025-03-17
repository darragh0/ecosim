package ecosim.game_engine.enm;
/**
 * Enum representing the different times of day in the game.
 * @author Kabidoye-17
 */
import static ecosim.common.Util.title;

public enum TimeOfDay implements Event {
    DAY("🌅"),
    NIGHT("🌙");

    private final String icon;

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        return title(this.name());
    }

    TimeOfDay(String icon) {
        this.icon = icon;
    }
}

