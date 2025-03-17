package ecosim.ui.view;

/**
 * Splash screen view for the ecosystem simulator.
 * Displays welcome screen and handles program start/end messages.
 * @author Kabidoye-17
 */

import static ecosim.common.io.ConsoleIO.clearTerminal;
import static ecosim.common.io.ConsoleIO.cursorUp;
import static ecosim.common.io.ConsoleIO.getTermLines;
import static ecosim.common.io.ConsoleIO.pprintCenter;
import static ecosim.common.io.ConsoleIO.pprintln;
import static ecosim.common.io.ConsoleIO.strInput;
import static ecosim.common.io.ConsoleIO.toggleCursor;


public final class SplashScreenView {

    private static final short TITLE_HEIGHT = 7;
    private static final String TITLE = """
                                [flc:_        \s
          ___  _________  _____(_)___ ___\s
         / _ \\/ ___/ __ \\/ ___/ / __ `__ \\
        /  __/ /__/ /_/ (__  ) / / / / / /
        \\___/\\___/\\____/____/_/_/ /_/ /_/\s

        ][fly:An Ecosystem Simulator]""";

    private static final short GRAPHIC_HEIGHT = 9;
    private static final String GRAPHIC = """
        **          [flg:_-_]                         [flm:/^-^\\]      \s
               [flg:/~~   ~~\\]            [fly:.-",]                 \s
            [flg:/~~         ~~\\]           [fly:||]             [flr:/^-^\\]
           [flg:{               }]          [fly:||]                \s
            [flg:\\  _-     -_  /]          [fly:(':.)`]             \s
              [flg:~  \\   /  ~]            [fly:|| ||]              \s
                  [fdy:| |]                [fly:|| ||]              \s
                  [fdy:| |]                [fly:|| ||]              \s
        [flg:⣠⣤⣤⣤⣀⣠⣤⣤⣤⣤⣤⣤⣀⣤⣤⣤⣄⣀⣤⣤⣤⣤⣄⣀⣤⣤⣤⣄⣀⣄⣀⣤⣤⣤⣤⣤⣤⣀⣤⣤⣤⣄⣀⣤⣤⣤⣤⣄⣀⣤⣤⣤⣄⣀⣄⣀⣤⣤⣤⣤⣄⣀⣤⣤⣤⣄⣀⣤⣤⣤⣤⣄⣀⣠⣄]**""";

    /**
     * Private constructor to prevent instantiation
     */
    private SplashScreenView() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Displays the splash screen and waits for user input
     */
    public static void show() {
        final int gap = Math.max(getTermLines() - TITLE_HEIGHT - GRAPHIC_HEIGHT - 1, 10);

        clearTerminal();
        pprintCenter(TITLE);
        System.out.print("\n".repeat(gap));
        pprintCenter(GRAPHIC);
        cursorUp((gap / 2) + GRAPHIC_HEIGHT);
        toggleCursor(false);
        pprintCenter("Press **[flr:Enter]** to start");
        strInput(true);
        toggleCursor(true);
        clearTerminal();
    }

    /**
     * Displays welcome message and setup instructions
     */
    public void welcome() {
        SplashScreenView.show();
        pprintln("Welcome to the *Ecosystem Simulator* 🌳");
        pprintln("To setup the ecosystem, please follow the prompts below.\n");
    }

    /**
     * Displays exit message with program status code
     * 
     * @param exitCode The exit status code
     */
    public void end(int exitCode) {
        toggleCursor(true);
        pprintln("\n[flr:(Simulator finished w/ exit code %d)]", exitCode);
    }
}
