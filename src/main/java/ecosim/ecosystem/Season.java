package ecosim.ecosystem;

public enum Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER;

    public Season getNextSeason() {
        return switch (this) {
            case SPRING -> SUMMER;
            case SUMMER -> AUTUMN;
            case AUTUMN -> WINTER;
            case WINTER -> SPRING;
        };
    }
}