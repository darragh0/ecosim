package ecosim.organism.animal.factory;


import ecosim.enm.Biome;


public class AnimalFactoryProducer {

    public static AnimalFactory getFactory(final Biome biome) {
        return switch (biome) {
            case Biome.DESERT -> new ConcreteDesertAnimalFactory();
            case Biome.GRASSLAND -> new ConcreteGrasslandAnimalFactory();
            default -> throw new IllegalArgumentException("Cannot create animal factory for biome: " + biome);
        };
    }

}

