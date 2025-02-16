package ecosim;


public enum Season {
    SPRING(0),
    SUMMER(1),
    AUTUMN(2),
    WINTER(3);

    private final int ordinal;

    private Season(final int ord) {
        this.ordinal = ord;
    }

    public int getValue() {
        return this.ordinal;
    }

    public static Season fromOrdinal(final int ord) {
        for (final Season season : Season.values()) {
            if (season.ordinal == ord) {
                return season;
            }
        }

        throw new IllegalArgumentException("Invalid ordinal value (got %d, expected 0-3)".formatted(ord));
    }

}
