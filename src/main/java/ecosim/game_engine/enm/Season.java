package ecosim.game_engine.enm;
/**
 * Enum representing the different seasons in the game.
 * @author darragh0
 */
import static ecosim.common.Util.title;

public enum Season implements Event {
    SPRING("🌸"),
    SUMMER("🏖️"),
    AUTUMN("🍂"),
    WINTER("☃️");

    private final String icon;

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        return title(this.name());
    }

    Season(String icon) {   
        this.icon = icon;
    }
    
}

