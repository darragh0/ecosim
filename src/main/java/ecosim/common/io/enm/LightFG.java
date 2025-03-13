package ecosim.common.io.enm;


public enum LightFG {
    RED("\033[91m"),
    GREEN("\033[92m"),
    YELLOW("\033[93m"),
    BLUE("\033[94m"),
    MAGENTA("\033[95m"),
    CYAN("\033[96m"),
    WHITE("\033[97m");

    private final String code;

    private LightFG(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

