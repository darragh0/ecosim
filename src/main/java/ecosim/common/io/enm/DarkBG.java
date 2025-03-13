package ecosim.common.io.enm;


public enum DarkBG {
    RED("\033[41m"),
    GREEN("\033[42m"),
    YELLOW("\033[43m"),
    BLUE("\033[44m"),
    MAGENTA("\033[45m"),
    CYAN("\033[46m"),
    WHITE("\033[47m");

    private final String code;

    private DarkBG(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

