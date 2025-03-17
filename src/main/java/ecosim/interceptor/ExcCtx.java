package ecosim.interceptor;


/**
 * Context class for exceptions.
 * 
 * @author darragh0
 */
class ExcCtx {

    private final Exception exception;
    private final String caller;
    private final long timestamp;
    private final int exitCode;
    private boolean handled = false;

    ExcCtx(final Exception exception, final String caller, final int exitCode) {
        this.exception = exception;
        this.caller = caller;
        this.timestamp = System.currentTimeMillis();
        this.exitCode = exitCode;
    }

    Exception getException() {
        return exception;
    }

    String getCaller() {
        return caller;
    }

    long getTimestamp() {
        return timestamp;
    }

    int getExitCode() {
        return exitCode;
    }

    void setHandled(final boolean handled) {
        this.handled = handled;
    }

    boolean isHandled() {
        return handled;
    }

}
