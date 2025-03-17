package ecosim.game_engine.misc;

import ecosim.game_engine.enm.Size;

public record PlantDescriptor(
    String name,
    Size size,
    String symbol) implements Descriptor {
}