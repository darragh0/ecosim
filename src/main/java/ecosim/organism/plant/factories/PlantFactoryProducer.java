package ecosim.organism.plant.factories;

public class PlantFactoryProducer {
    
    public static PlantFactory getFactory(String biome) {
        return switch (biome) {
            case "Desert" -> new ConcreteDesertPlantFactory();
            case "Grassland" -> new ConcreteGrasslandPlantFactory();
            default -> throw new IllegalArgumentException("Unknown biome: " + biome);
        };
    }
}
