package ecosim.enm;


public enum TimeOfDay implements Event {
    DAY("🌅"),
    NIGHT("🌙");

    private final String icon;

    TimeOfDay(String icon) {
        this.icon = icon;
    }
}
