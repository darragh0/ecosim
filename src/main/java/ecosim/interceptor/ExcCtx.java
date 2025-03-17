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
    private final String logMessage;
    private final Object[] logArgs;
    private boolean handled = false;

    ExcCtx(
        final Exception exception,
        final String caller,
        final int exitCode,
        final String logMessage,
        final Object... logArgs) {

        this.exception = exception;
        this.caller = caller;
        this.timestamp = System.currentTimeMillis();
        this.exitCode = exitCode;
        this.logMessage = logMessage;
        this.logArgs = logArgs;
    }

    Exception getException() {
        return this.exception;
    }

    String getCaller() {
        return this.caller;
    }

    long getTimestamp() {
        return this.timestamp;
    }

    int getExitCode() {
        return this.exitCode;
    }

    String getLogMessage() {
        return String.format(this.logMessage, this.logArgs);
    }

    void setHandled(final boolean handled) {
        this.handled = handled;
    }

    boolean isHandled() {
        return handled;
    }

}
