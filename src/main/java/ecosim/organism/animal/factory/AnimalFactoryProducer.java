package ecosim.organism.animal.factory;


import ecosim.organism.animal.Animal;


public class AnimalFactoryProducer {

    public static AnimalFactory<? extends Animal> getFactory(String biome) {
        return switch (biome) {
            case "DESERT" -> new ConcreteDesertAnimalFactory();
            case "GRASSLAND" -> new ConcreteGrasslandAnimalFactory();
            default -> throw new IllegalArgumentException("Unknown biome: " + biome);
        };
    }

}

