package ecosim.common.io.enm;


public enum Color {
    RED("\033[91m"),
    GREEN("\033[92m"),
    YELLOW("\033[93m"),
    BLUE("\033[94m"),
    MAGENTA("\033[95m"),
    CYAN("\033[96m"),
    NONE("\033[0;0m");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Color from(char ch) {
        for (Color style : Color.values()) {
            if (style.toString().charAt(0) == Character.toUpperCase(ch)) {
                return style;
            }
        }
        return Color.NONE;
    }

}
