package ecosim.ui.menu;


import java.util.List;

import ecosim.game_engine.enm.Biome;


/**
 * Menu for selecting biome.
 * Allows users to choose from a list of available ecosystem biomes.
 * 
 * @author darragh0
 */
public class BiomeMenu extends Menu<Biome> {

    /**
     * Creates a biome selection menu from a list of biomes.
     * 
     * @param options List of available biomes
     */
    public BiomeMenu(final List<Biome> options) {
        super("Available Biomes", options);
    }

    /**
     * Creates a biome selection menu from a varargs array of biomes.
     * 
     * @param options Array of available biomes
     */
    public BiomeMenu(final Biome... options) {
        this(List.of(options));
    }

}
