package ecosim.common.io.enm;


public enum LightBG {
    RED("\033[101m"),
    GREEN("\033[102m"),
    YELLOW("\033[103m"),
    BLUE("\033[104m"),
    MAGENTA("\033[105m"),
    CYAN("\033[106m"),
    WHITE("\033[107m");

    private final String code;

    private LightBG(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
