package ecosim.enm;


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
        return  this.name().toLowerCase();
    }

    TimeOfDay(String icon) {
        this.icon = icon;
    }
}

