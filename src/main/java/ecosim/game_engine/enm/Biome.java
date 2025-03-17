package ecosim.game_engine.enm;

/**
 * Enum representing the different biomes in the game.
 * @author MiaBorkoo 
 */
import static ecosim.common.Util.title;


public enum Biome {
    DESERT,
    GRASSLAND;

    @Override
    public String toString() {
        return title(this.name());
    }

}
