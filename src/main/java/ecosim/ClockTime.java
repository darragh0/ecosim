package ecosim;


/**
 * A class to represent game time (second, minute, hour, day, season).
 * 
 * @author darragh0
 */
public final class ClockTime {

    private static final int DAYS_IN_SEASON = 10;
    private int sex;

    public ClockTime(final int sex) {
        this.sex = sex;
    }

    public ClockTime() {
        this(0);
    }

    public void increment() {
        this.sex++;
    }

    public int getSec() {
        return this.sex % 60;
    }

    public int getMin() {
        return this.sex / 60 % 60;
    }

    public int getHour() {
        return this.sex / (60 * 60) % 24;
    }

    public int getDay() {
        return this.sex / (60 * 60 * 24) % DAYS_IN_SEASON + 1;
    }

    public Day getWeekday() {
        return Day.fromOrdinal(this.sex / (60 * 60 * 24) % 7);
    }

    public Season getSeason() {
        return Season.fromOrdinal(this.sex / (60 * 60 * 24 * DAYS_IN_SEASON) % 4);
    }

    public int getYear() {
        return this.sex / (60 * 60 * 24 * DAYS_IN_SEASON * 4);
    }

    @Override
    public String toString() {
        return "%s, %s %02d, %02d:%02d:%02d ".formatted(
            this.getWeekday().toString(),
            this.getSeason().toString(),
            this.getDay(),
            this.getHour(),
            this.getMin(),
            this.getSec());
    }

    public static ClockTime getRandomTimestamp() {
        return new ClockTime((int) (Math.random() * 60 * 60 * 24 * 28 * 4));
    }

}


