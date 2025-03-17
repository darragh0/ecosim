package ecosim.ui.menu;


import java.util.List;

import ecosim.enm.Biome;


/**
 * Menu for selecting biome.
 * 
 * @author darragh0
 */
public class BiomeMenu extends Menu<Biome> {

    public BiomeMenu(final List<Biome> options) {
        super("Available Biomes", options);
    }

    public BiomeMenu(final Biome... options) {
        this(List.of(options));
    }

}
