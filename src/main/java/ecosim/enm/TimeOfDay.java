package ecosim.enm;

public enum TimeOfDay implements Event {
    DAY("Day"),
    NIGHT("Night");

    private final String value;

    TimeOfDay(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
