package ecosim.enm;

public enum Weather implements Event {
    RAINY("Rainy"),
    SUNNY("Sunny"),
    DRY("Dry"),
    CLOUDY("Cloudy"),
    SNOWY("Snowy");

    private final String value;

    Weather(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
