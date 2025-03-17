package ecosim.interceptor;


import java.util.ArrayList;
import java.util.List;


/**
 * Dispatcher class to manage and notify exception interceptors.
 * 
 * @author darragh0
 */
public class ExcDispatcher {

    private static final List<ExcInterceptor> interceptors = new ArrayList<>();

    public static void addInterceptor(final ExcInterceptor interceptor) {
        interceptors.add(interceptor);
    }

    public static void rmvInterceptor(final ExcInterceptor interceptor) {
        interceptors.remove(interceptor);
    }

    public static void dispatchException(final Exception exception, final String caller, final int exitCode) {
        final ExcCtx ctx = new ExcCtx(exception, caller, exitCode);

        for (final ExcInterceptor in : ExcDispatcher.interceptors) {
            in.intercept(ctx);
            if (ctx.isHandled())
                break;
        }

        System.exit(exitCode);
    }

}
