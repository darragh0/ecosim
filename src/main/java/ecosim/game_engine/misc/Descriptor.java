package ecosim.game_engine.misc;


import ecosim.game_engine.enm.Size;

/**
 * Base interface for organism descriptors.
 * Provides common properties required by all organism types.
 * @author darragh0
 */
public interface Descriptor {

    /**
     * Gets the size of the organism.
     * 
     * @return The size enum value
     */
    Size size();

    /**
     * Gets the symbol representing the organism.
     * 
     * @return The string symbol (often an emoji)
     */
    String symbol();

}
