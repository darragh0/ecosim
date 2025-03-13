package ecosim.enm;


public enum Weather implements Event {
    RAINY("🌧️"),
    SUNNY("☀️"),
    DRY("💨"),
    CLOUDY("☁️"),
    SNOWY("❄️");

    private final String icon;

    Weather(String icon) {
        this.icon = icon ;
    }
}
