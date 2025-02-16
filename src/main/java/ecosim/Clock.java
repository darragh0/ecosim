package ecosim;


public final class Clock {

    private final ClockTime time;
    private int tickMs = 1;

    public Clock() {
        this.time = new ClockTime();
    }

    public ClockTime getTimestamp() {
        return this.time;
    }

    public int getTickMs() {
        return this.tickMs;
    }

    public void setTickMs(int ms) {
        this.tickMs = ms;
    }

    public void tick() {
        this.time.increment();
        this.sleep();
    }

    public void displayTime() {
        System.out.printf("%s\r", this.time);
    }

    private void sleep() {
        try {
            Thread.sleep(this.tickMs);
        } catch (InterruptedException ignored) {
        }
    }

}

