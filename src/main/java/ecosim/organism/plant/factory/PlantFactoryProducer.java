package ecosim.organism.plant.factory;


import ecosim.organism.plant.Plant;


public class PlantFactoryProducer {

    public static PlantFactory<? extends Plant> getFactory(String biome) {
        return switch (biome) {
            case "DESERT" -> new ConcreteDesertPlantFactory();
            case "GRASSLAND" -> new ConcreteGrasslandPlantFactory();
            default -> throw new IllegalArgumentException("Unknown biome: " + biome);
        };
    }

}
