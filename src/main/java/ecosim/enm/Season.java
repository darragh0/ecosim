package ecosim.enm;


public enum Season implements Event {
    SPRING("🌸"),
    SUMMER("🏖️"),
    AUTUMN("🍂"),
    WINTER("☃️");

    private final String icon;

    Season(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

}

