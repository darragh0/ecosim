package ecosim.view;


import static ecosim.common.io.ConsoleIO.clearTerminal;
import static ecosim.common.io.ConsoleIO.getTermLines;
import static ecosim.common.io.ConsoleIO.cursorUp;
import static ecosim.common.io.ConsoleIO.pprintCenter;
import static ecosim.common.io.ConsoleIO.strInput;
import static ecosim.common.io.ConsoleIO.toggleCursor;


public final class SplashScreen {

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

    private SplashScreen() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

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

}
