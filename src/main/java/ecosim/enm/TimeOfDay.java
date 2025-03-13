package ecosim.enm;

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

