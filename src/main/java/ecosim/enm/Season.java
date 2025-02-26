package ecosim.enm;

public enum Season implements Event {
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    WINTER("Winter");

    private final String value;

    Season(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
