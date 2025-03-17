package ecosim.game_engine.organism.factory;

import ecosim.game_engine.enm.Biome;

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
    public static BiomeOrganismFactory getFactory(Biome biome) {
        return switch (biome) {
            case DESERT -> new DesertOrganismFactory();
            case GRASSLAND -> new GrasslandOrganismFactory();
            default -> throw new IllegalArgumentException("Unsupported biome: " + biome);
        };
    }
}