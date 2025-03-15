package ecosim.menu;


import java.util.List;

import ecosim.enm.Biome;


public class BiomeMenu extends Menu<Biome> {

    public BiomeMenu(final List<Biome> options) {
        super("Select the Biome", options);
    }

    public BiomeMenu(final Biome... options) {
        this(List.of(options));
    }

}
