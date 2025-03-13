package ecosim.enm;


public enum Season implements Event {
    SPRING("🌸"),
    SUMMER("🏖️"),
    AUTUMN("🍂"),
    WINTER("☃️");

    private final String icon;

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    Season(String icon) {   
        this.icon = icon;
    }
    
}

