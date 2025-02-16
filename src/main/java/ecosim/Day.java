package ecosim;


public enum Day {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6);

    private final int ordinal;

    private Day(final int ord) {
        this.ordinal = ord;
    }

    public int getValue() {
        return this.ordinal;
    }

    public static Day fromOrdinal(final int ord) {
        for (final Day day : Day.values()) {
            if (day.ordinal == ord) {
                return day;
            }
        }

        throw new IllegalArgumentException("Invalid ordinal value (got %d, expected 0-6)".formatted(ord));
    }

}
