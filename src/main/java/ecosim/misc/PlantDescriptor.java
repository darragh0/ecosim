package ecosim.misc;


import ecosim.enm.Size;
import ecosim.organism.plant.abs.Plant;


public record PlantDescriptor(
    Class<? extends Plant> plantClass,
    Size size,
    String symbol) implements Descriptor {
}
