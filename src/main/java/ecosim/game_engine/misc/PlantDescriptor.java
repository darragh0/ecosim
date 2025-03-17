package ecosim.misc;

import ecosim.enm.Size;

public record PlantDescriptor(
    String name,
    Size size,
    String symbol) implements Descriptor {
}