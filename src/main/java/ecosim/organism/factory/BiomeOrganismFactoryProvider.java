package ecosim.organism.factory;

/**
 * Provider for biome-specific organism factories.
 */
public class BiomeOrganismFactoryProvider {
    
    /**
     * Get the appropriate factory for the specified biome.
     * 
     * @param biome The biome to get a factory for
     * @return A factory instance for the biome
     */
    public static BiomeOrganismFactory getFactory(String biome) {
        return switch (biome) {
            case "DESERT" -> new DesertOrganismFactory();
            case "GRASSLAND" -> new GrasslandOrganismFactory();
            default -> throw new IllegalArgumentException("Unsupported biome: " + biome);
        };
    }
}