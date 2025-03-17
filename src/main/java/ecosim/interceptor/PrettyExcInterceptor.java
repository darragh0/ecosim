package ecosim.interceptor;


import static ecosim.common.io.ConsoleIO.pprintln;
import static ecosim.common.io.ConsoleIO.ynInput;

import java.util.Date;


/**
 * Pretty exception interceptor.
 * 
 * @author darragh0
 */
public class PrettyExcInterceptor implements ExcInterceptor {

    @Override
    public void intercept(final ExcCtx context) {
        final Exception e = context.getException();

        pprintln("**[flr:Oh no! An error occurred!]**\n");
        pprintln("[flr:Invoker:] %s", context.getCaller());
        pprintln("[flr:Time:] %s", new Date(context.getTimestamp()));
        pprintln("[flr:Type:] %s", e.getClass().getSimpleName());
        pprintln("[flr:Message:] %s\n", e.getMessage());

        final boolean showStackTrace = ynInput("Would you like to see the stack trace? (y/n): ");
        if (showStackTrace) {
            pprintln("[flr:Stack trace:]");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                pprintln("  ([fly:%d]) at %s", i, e.getStackTrace()[i]);
            }
        }
        System.out.println();

        context.setHandled(true);
    }

}
