package ecosim.ecosystem;

public enum TimeOfDay {
    DAY,
    NIGHT;

    public TimeOfDay switchTimeOfDay() {
        return switch (this) {
            case DAY -> NIGHT;
            case NIGHT -> DAY;
        };
    }
}