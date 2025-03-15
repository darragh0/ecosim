package ecosim.common.io.enm;


public enum Reset {
    FG("\033[39m"),
    BG("\033[49m"),
    BOLD("\033[22m"),
    ITALIC("\033[23m"),
    UNDERLINE("\033[24m"),
    REVERSE("\033[27m"),
    STRIKETHROUGH("\033[29m"),
    ALL("\033[0m");

    private final String code;

    private Reset(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
