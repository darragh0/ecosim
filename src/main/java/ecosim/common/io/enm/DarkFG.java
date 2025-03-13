package ecosim.common.io.enm;


public enum DarkFG {
    RED("\033[31m"),
    GREEN("\033[32m"),
    YELLOW("\033[33m"),
    BLUE("\033[34m"),
    MAGENTA("\033[35m"),
    CYAN("\033[36m"),
    WHITE("\033[37m");

    private final String code;

    private DarkFG(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

