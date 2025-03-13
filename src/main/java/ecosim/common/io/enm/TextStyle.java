package ecosim.common.io.enm;


public enum TextStyle {
    BOLD("\033[1m"),
    ITALIC("\033[3m"),
    UNDERLINE("\033[4m"),
    REVERSE("\033[7m"),
    STRIKETHROUGH("\033[9m"),
    RESET("\033[0m");

    private final String code;

    private TextStyle(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

