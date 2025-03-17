package ecosim.interceptor;


import static ecosim.common.io.ConsoleIO.pprintln;
import static ecosim.common.io.ConsoleIO.ynInput;

import java.util.Date;
import java.util.logging.Level;

import ecosim.game_engine.man.LoggerMan;


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
        pprintln("[flr:Description:] %s", e.getMessage());

        if (context.getLogMessage() != null) {
            pprintln("[flr:Message:] %s", context.getLogMessage());
            LoggerMan.log(Level.SEVERE, context.getLogMessage());
        }
        pprintln("[flr:Exit code:] %d\n", context.getExitCode());

        final boolean showStackTrace = ynInput("Would you like to see the stack trace? (y/n): ");
        if (showStackTrace) {
            pprintln("[flr:Stack trace:]");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                pprintln("  ([fly:%d]) at %s", i, e.getStackTrace()[i]);
            }
        }

        context.setHandled(true);
    }

}
