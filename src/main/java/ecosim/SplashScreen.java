package ecosim;


import static ecosim.common.io.ConsoleIO.clearTerminal;
import static ecosim.common.io.ConsoleIO.getTermLines;
import static ecosim.common.io.ConsoleIO.moveCursorUp;
import static ecosim.common.io.ConsoleIO.prettyPrintCenter;
import static ecosim.common.io.ConsoleIO.strInput;
import static ecosim.common.io.ConsoleIO.toggleCursor;


public final class SplashScreen {

    private static final short TITLE_HEIGHT = 7;
    private static final String TITLE = """
                                <B><c>_        \s
          ___  _________  _____(_)___ ___\s
         / _ \\/ ___/ __ \\/ ___/ / __ `__ \\
        /  __/ /__/ /_/ (__  ) / / / / / /
        \\___/\\___/\\____/____/_/_/ /_/ /_/\s

        </c><y>An Ecosystem Simulator</y></B>""";

    private static final short GRAPHIC_HEIGHT = 9;
    private static final String GRAPHIC = """
        <B>          <g>_-_</g>                         <m>/^-^\\</m>      \s
               <g>/~~   ~~\\</g>           <y>.-",</y>                 \s
            <g>/~~         ~~\\</g>          <y>||</y>             <r>/^-^\\</r>
           <g>{               }</g>          <y>||</y>                \s
            <g>\\  _-     -_  /</g>          <y>(':.)`</y>             \s
              <g>~  \\   /  ~</g>            <y>|| ||</y>              \s
                  | |                <y>|| ||</y>              \s
                  | |                <y>|| ||</y>              \s
        <g>⣠⣤⣤⣤⣀⣠⣤⣤⣤⣤⣤⣤⣀⣤⣤⣤⣄⣀⣤⣤⣤⣤⣄⣀⣤⣤⣤⣄⣀⣄⣀⣤⣤⣤⣤⣤⣤⣀⣤⣤⣤⣄⣀⣤⣤⣤⣤⣄⣀⣤⣤⣤⣄⣀⣄⣀⣤⣤⣤⣤⣄⣀⣤⣤⣤⣄⣀⣤⣤⣤⣤⣄⣀⣠⣄</g></B>""";

    private SplashScreen() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static void show() {
        final int gap = Math.max(getTermLines() - TITLE_HEIGHT - GRAPHIC_HEIGHT - 1, 10);

        clearTerminal();
        prettyPrintCenter(TITLE);
        System.out.print("\n".repeat(gap));
        prettyPrintCenter(GRAPHIC);
        moveCursorUp((gap / 2) + GRAPHIC_HEIGHT);
        toggleCursor(false);
        prettyPrintCenter("Press <B><r>Enter</r></B> to start");
        strInput(true);
        toggleCursor(true);
        clearTerminal();
    }

}
