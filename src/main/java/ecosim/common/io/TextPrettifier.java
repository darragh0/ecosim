package ecosim.common.io;


import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import ecosim.common.io.enm.DarkBG;
import ecosim.common.io.enm.DarkFG;
import ecosim.common.io.enm.LightBG;
import ecosim.common.io.enm.LightFG;
import ecosim.common.io.enm.Reset;
import ecosim.common.io.enm.TextStyle;


final class TextPrettifier {

    private static final Map<String, String> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put("flr", LightFG.RED.getCode());
        COLOR_MAP.put("flg", LightFG.GREEN.getCode());
        COLOR_MAP.put("fly", LightFG.YELLOW.getCode());
        COLOR_MAP.put("flb", LightFG.BLUE.getCode());
        COLOR_MAP.put("flm", LightFG.MAGENTA.getCode());
        COLOR_MAP.put("flc", LightFG.CYAN.getCode());
        COLOR_MAP.put("flw", LightFG.WHITE.getCode());

        COLOR_MAP.put("fdr", DarkFG.RED.getCode());
        COLOR_MAP.put("fdg", DarkFG.GREEN.getCode());
        COLOR_MAP.put("fdy", DarkFG.YELLOW.getCode());
        COLOR_MAP.put("fdb", DarkFG.BLUE.getCode());
        COLOR_MAP.put("fdm", DarkFG.MAGENTA.getCode());
        COLOR_MAP.put("fdc", DarkFG.CYAN.getCode());
        COLOR_MAP.put("fdw", DarkFG.WHITE.getCode());

        COLOR_MAP.put("blr", LightBG.RED.getCode());
        COLOR_MAP.put("blg", LightBG.GREEN.getCode());
        COLOR_MAP.put("bly", LightBG.YELLOW.getCode());
        COLOR_MAP.put("blb", LightBG.BLUE.getCode());
        COLOR_MAP.put("blm", LightBG.MAGENTA.getCode());
        COLOR_MAP.put("blc", LightBG.CYAN.getCode());
        COLOR_MAP.put("blw", LightBG.WHITE.getCode());

        COLOR_MAP.put("bdr", DarkBG.RED.getCode());
        COLOR_MAP.put("bdg", DarkBG.GREEN.getCode());
        COLOR_MAP.put("bdy", DarkBG.YELLOW.getCode());
        COLOR_MAP.put("bdb", DarkBG.BLUE.getCode());
        COLOR_MAP.put("bdm", DarkBG.MAGENTA.getCode());
        COLOR_MAP.put("bdc", DarkBG.CYAN.getCode());
        COLOR_MAP.put("bdw", DarkBG.WHITE.getCode());
    }

    private static String applyStyle(String txt) {
        txt = txt.replaceAll("\\*\\*(.*?)\\*\\*", TextStyle.BOLD.getCode() + "$1" + Reset.BOLD.getCode());
        txt = txt.replaceAll("\\*(.*?)\\*", TextStyle.ITALIC.getCode() + "$1" + Reset.ITALIC.getCode());
        txt = txt.replaceAll("#(.*?)#", TextStyle.UNDERLINE.getCode() + "$1" + Reset.UNDERLINE.getCode());
        txt = txt.replaceAll("-=-(.*?)-=-", TextStyle.STRIKETHROUGH.getCode() + "$1" + Reset.STRIKETHROUGH.getCode());
        txt = txt.replaceAll("\\^^(.*?)\\^^", TextStyle.REVERSE.getCode() + "$1" + Reset.REVERSE.getCode());
        return txt;
    }

    private static String applyColor(final String txt) {
        final StringBuilder result = new StringBuilder();
        final Stack<String> tagStack = new Stack<>();

        int i = 0;
        while (i < txt.length()) {
            char c = txt.charAt(i);

            // Look for opening color tag [xxx:
            if (c == '[' && i + 5 < txt.length() && txt.charAt(i + 4) == ':') {
                final String potentialTag = txt.substring(i + 1, i + 4).toLowerCase();

                if (COLOR_MAP.containsKey(potentialTag)) {
                    // Valid color tag found
                    tagStack.push(potentialTag);
                    result.append(COLOR_MAP.get(potentialTag));
                    i += 5; // Skip past the [tag:
                } else {
                    // Not a valid tag, treat as regular char
                    result.append(c);
                    i++;
                }
            }
            // Look for closing bracket
            else if (c == ']' && !tagStack.isEmpty()) {
                final String tag = tagStack.pop();

                // Reset appropriate color component
                if (tag.startsWith("f")) {
                    result.append(Reset.FG.getCode());
                } else if (tag.startsWith("b")) {
                    result.append(Reset.BG.getCode());
                }

                // Reapply any remaining colors in the stack
                for (String remainingTag : tagStack) {
                    result.append(COLOR_MAP.get(remainingTag));
                }

                i++; // Move past the closing bracket
            }
            // Regular character
            else {
                result.append(c);
                i++;
            }
        }

        return result.toString();
    }

    public static String prettify(String fstr, final Object... fargs) {
        fstr = String.format(fstr, fargs);
        return applyColor(applyStyle(fstr));
    }

}
