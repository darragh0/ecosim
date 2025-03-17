package ecosim.game_engine.misc;

import ecosim.game_engine.enm.Size;

/**
 * Record that describes a plant's properties.
 * Used as a blueprint for creating plant instances.
 * @author darragh0
 */
public record PlantDescriptor(
    String name,
    Size size,
    String symbol) implements Descriptor {
}