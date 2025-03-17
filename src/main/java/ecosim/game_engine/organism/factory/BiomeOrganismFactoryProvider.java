package ecosim.game_engine.organism.factory;

import ecosim.game_engine.enm.Biome;

/**
 * Provider for biome-specific organism factories.
 * Selects the appropriate factory implementation based on the biome type.
 * @author Kabidoye-17
 */
public class BiomeOrganismFactoryProvider {
    
    /**
     * Get the appropriate factory for the specified biome.
     * 
     * @param biome The biome to get a factory for
     * @return A factory instance for the biome
     * @throws IllegalArgumentException if an unsupported biome is provided
     */
    public static BiomeOrganismFactory getFactory(Biome biome) {
        return switch (biome) {
            case DESERT -> new DesertOrganismFactory();
            case GRASSLAND -> new GrasslandOrganismFactory();
            default -> throw new IllegalArgumentException("Unsupported biome: " + biome);
        };
    }
}