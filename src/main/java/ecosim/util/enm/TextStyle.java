package ecosim.util.enm;


public enum TextStyle {
    BOLD("\033[1m"),
    ITALIC("\033[3m"),
    UNDERLINE("\033[4m"),
    REVERSED("\033[7m"),
    STRIKETHROUGH("\033[9m"),
    NONE("\033[0;0m");

    private final String value;

    TextStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static TextStyle from(char ch) {
        for (TextStyle style : TextStyle.values()) {
            if (style.toString().charAt(0) == Character.toUpperCase(ch)) {
                return style;
            }
        }
        return TextStyle.NONE;
    }

}
