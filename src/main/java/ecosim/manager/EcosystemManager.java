package ecosim.manager;


import ecosim.Clock;
import static ecosim.util.io.ConsoleIO.closeConsoleInputSource;
import static ecosim.util.io.ConsoleIO.println;


public final class EcosystemManager {

    private static final String WELCOME_MSG = """
        <B>
        ███████╗░█████╗░░█████╗░░██████╗██╗███╗░░░███╗
        ██╔════╝██╔══██╗██╔══██╗██╔════╝██║████╗░████║
        █████╗░░██║░░╚═╝██║░░██║╚█████╗░██║██╔████╔██║
        ██╔══╝░░██║░░██╗██║░░██║░╚═══██╗██║██║╚██╔╝██║
        ███████╗╚█████╔╝╚█████╔╝██████╔╝██║██║░╚═╝░██║
        ╚══════╝░╚════╝░░╚════╝░╚═════╝░╚═╝╚═╝░░░░░╚═╝

        =========   <y>An Ecosystem Simulator</y>   =========
        </B>""";

    private EcosystemManager() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static void welcome() {
        println(WELCOME_MSG);
    }

    public static void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(EcosystemManager::exit));
        println("<B><g>[Simulator started]</g></B>\n");
        welcome();

        Clock clock = new Clock();
        // simulator loop here
        while (true) {
            clock.displayTime();
            clock.tick();
        }
    }

    public static void exit() {
        int exitCode = 0;
        println("\n<B><r>[Simulator finished w/ exit code %d]</r></B>", exitCode);
        closeConsoleInputSource();
        // System.exit(exitCode);
    }

}
