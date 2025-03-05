package ecosim.common;


import java.util.concurrent.ThreadLocalRandom;


public final class Util {

    private Util() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Return random int in given range (inclusive).
     * 
     * @param min Min. range value
     * @param max Max. range value
     */
    public static int randInt(final int min, final int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /** 
     * Return random int in range 0 to max (inclusive).
     * 
     * @see #randInt(int, int)
     */
    public static int randInt(final int max) {
        return randInt(0, max);
    }

}
