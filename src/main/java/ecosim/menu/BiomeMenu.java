package ecosim.menu;


import java.util.List;

import static ecosim.common.Util.title;
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
        return title(this.options.get(index).name());
    }

}
