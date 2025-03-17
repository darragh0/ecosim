package ecosim.game_engine.enm;


import static ecosim.common.Util.title;


public enum Biome {
    DESERT,
    GRASSLAND;

    @Override
    public String toString() {
        return title(this.name());
    }

}
