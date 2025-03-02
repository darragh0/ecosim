package ecosim.common.io.enm;


public enum BoxDrawingChar {
    HORIZONTAL("─"),
    TOP_LEFT("┌"),
    TOP_RIGHT("┐"),
    VERTICAL("│"),
    BOTTOM_LEFT("└"),
    BOTTOM_RIGHT("┘");

    private final String value;

    BoxDrawingChar(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String repeat(int n) {
        return this.value.repeat(n);
    }

}
