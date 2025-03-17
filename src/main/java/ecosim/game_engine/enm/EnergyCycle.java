package ecosim.game_engine.enm;


import static ecosim.common.Util.title;


public enum EnergyCycle {
    PHOTOSYNTHESIS,
    RESPIRATION;

    @Override
    public String toString() {
        return title(this.name());
    }

}

