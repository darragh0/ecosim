package ecosim.organism.animal.factory;

public class AnimalFactoryProducer {
    public static AnimalFactory getFactory(String biome) {
        return switch (biome) {
            case "Desert" -> new ConcreteDesertAnimalFactory();
            case "Grassland" -> new ConcreteGrasslandAnimalFactory();
            default -> throw new IllegalArgumentException("Unknown biome: " + biome);
        };
    }
}

