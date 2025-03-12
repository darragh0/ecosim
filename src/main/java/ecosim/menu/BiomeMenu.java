package ecosim.menu;


import java.util.List;

import ecosim.enm.Biome;


public class BiomeMenu extends Menu<Biome> {

    public BiomeMenu(final List<Biome> options) {
        super("Biome Selection", options);
    }

    public BiomeMenu(final Biome... options) {
        this(List.of(options));
    }

    @Override
    public String getOptionString(final int index) {
        String name = this.options.get(index).name();
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
