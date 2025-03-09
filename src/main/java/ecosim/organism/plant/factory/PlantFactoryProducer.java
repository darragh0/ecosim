package ecosim.organism.plant.factory;


import ecosim.enm.Biome;


public class PlantFactoryProducer {

    public static PlantFactory getFactory(final Biome biome) {
        return switch (biome) {
            case Biome.DESERT -> new ConcreteDesertPlantFactory();
            case Biome.GRASSLAND -> new ConcreteGrasslandPlantFactory();
            default -> throw new IllegalArgumentException("Cannot create plant factory for biome: " + biome);
        };
    }

}
