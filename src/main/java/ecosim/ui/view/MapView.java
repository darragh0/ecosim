package ecosim.ui.view;

/**
 * View responsible for displaying the ecosystem map.
 * Renders the grid with organisms using box drawing characters.
 * @author Kabidoye-17
 */

import static ecosim.common.io.ConsoleIO.add;
import static ecosim.common.io.ConsoleIO.pprintln;
import ecosim.common.io.enm.BoxDrawingChar;
import ecosim.game_engine.man.EcosystemMan;
import ecosim.game_engine.map.Grid;
import ecosim.game_engine.organism.Organism;

public class MapView {
    /**
     * Displays a graphical representation of the ecosystem map.
     * Shows all organisms in their current positions.
     * 
     * @param ecosystem The ecosystem manager containing map data
     */
     public void displayEcosytemMap(EcosystemMan ecosystem) {
        final String EMPTY_CELL = ".";
        final int height = ecosystem.getMapSize().height();
        final int width = ecosystem.getMapSize().width();
        Grid grid = ecosystem.getMapGrid();
        final StringBuilder sb = new StringBuilder();
        final String border = BoxDrawingChar.HORIZONTAL.repeat(width * 4); // Adjust border width for consistent cells

        add.accept(sb, "**🗺️ [fly:Where is Everyone?] 🗺️**\n");

        sb.append("**")
            .append(BoxDrawingChar.TOP_LEFT.getValue())
            .append(border)
            .append(BoxDrawingChar.TOP_RIGHT.getValue())
            .append("**\n");

        for (int y = height - 1; y >= 0; y--) {
            sb.append("**")
                .append(BoxDrawingChar.VERTICAL.getValue())
                .append("**");

            for (int x = 0; x < width; x++) {
                final String ch = grid.get(x, y)
                    .map(Organism::getSymbol)
                    .orElse(EMPTY_CELL);

                // Adjust spacing based on character type
                if (isWideCharacter(ch)) {
                    sb.append(" ").append(ch).append(" ");
                } else {
                    sb.append(" ").append(ch).append("  ");
                }
            }

            sb.append("**")
                .append(BoxDrawingChar.VERTICAL.getValue())
                .append("**\n");
        }

        sb.append("**")
            .append(BoxDrawingChar.BOTTOM_LEFT.getValue())
            .append(border)
            .append(BoxDrawingChar.BOTTOM_RIGHT.getValue())
            .append("**\n");

        pprintln(sb.toString());
    }

    /**
     * Helper method to determine if a character is wide (like emoji).
     * Ensures proper spacing in the map.
     * 
     * @param ch The character to check
     * @return true if the character is considered wide
     */
    private boolean isWideCharacter(String ch) {
        if (ch == null || ch.isEmpty())
            return false;

        // Simple heuristic: if it's not ASCII and not a common symbol, assume it's wide
        char c = ch.charAt(0);
        return c > 127 || ch.codePointAt(0) > 127;
    }
}
