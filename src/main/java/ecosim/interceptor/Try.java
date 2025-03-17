package ecosim.interceptor;


import java.util.function.Supplier;


/**
 * Utility class for executing code within the interceptor framework.
 * 
 * @author darragh0
 */
public class Try {

    public static void exec(final Runnable runnable, final int exitCode) {
        Try.exec(runnable, Try.getCaller(), exitCode);
    }

    public static <T> T exec(final Supplier<T> supplier, final int exitCode) {
        return Try.exec(supplier, Try.getCaller(), exitCode);
    }

    private static void exec(final Runnable runnable, final String caller, final int exitCode) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            ExcDispatcher.dispatchException(e, caller, exitCode);
        }
    }

    private static <T> T exec(final Supplier<T> supplier, final String caller, final int exitCode) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            ExcDispatcher.dispatchException(e, caller, exitCode);
            return null;
        }
    }

    private static String getCaller() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // Skip the first two elements (getStackTrace and getSource)
        // Then skip the exec method itself
        if (stackTrace.length > 3) {
            StackTraceElement caller = stackTrace[3];
            return caller.getClassName() + "." + caller.getMethodName();
        }
        return "Unknown";
    }

}

