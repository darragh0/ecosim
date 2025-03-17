package ecosim.game_engine.enm;
/**
 * Enum representing the different weather conditions in the game.
 * @author darragh0
 */
import static ecosim.common.Util.title;

public enum Weather implements Event {
    RAINY("🌧️"),
    SUNNY("☀️"),
    DRY("💨"),
    CLOUDY("☁️"),
    SNOWY("❄️");

    private final String icon;

    @Override
    public String getIcon() {
        return this.icon;
    }


    @Override
    public String toString() {
       return title(this.name());
    }

    Weather(String icon) {
        this.icon = icon ;
    }
}
