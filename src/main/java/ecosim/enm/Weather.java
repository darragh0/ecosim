package ecosim.enm;


public enum Weather implements Event {
    RAINY("🌧️"),
    SUNNY("☀️"),
    DRY("💨"),
    CLOUDY("☁️"),
    SNOWY("❄️");

    private final String icon;

    @Override
    public String getIcon() {
        return icon;
    }


    @Override
    public String toString() {
        return  this.name().toLowerCase();
    }

    Weather(String icon) {
        this.icon = icon ;
    }
}
