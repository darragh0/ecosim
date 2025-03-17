package ecosim.game_engine.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Central service for managing species numbering across the ecosystem.
 * Provides utilities for generating unique identifiers for organisms.
 * @author darragh0
 */
public class SpeciesNumbering {
    private static final Map<String, Integer> speciesCounter = new HashMap<>();
    
    /**
     * Gets the next number for a given species name.
     * Ensures each organism has a unique identifier.
     * 
     * @param speciesName The base name of the species
     * @return The next sequential number for this species
     */
    public static synchronized int getNextNumber(String speciesName) {
        // Add null check to prevent NullPointerException
        if (speciesName == null) {
            return 1;  // Default to 1 for null species names
        }
        
        // Trim the species name to handle any whitespace
        speciesName = speciesName.trim();
        
        int nextNumber = speciesCounter.getOrDefault(speciesName, 0) + 1;
        speciesCounter.put(speciesName, nextNumber);
        return nextNumber;
    }
    
    /**
     * Formats a species name with its number.
     * Creates identifiers in the format "Name (Number)".
     * 
     * @param speciesName The base name of the species
     * @param number The instance number
     * @return Formatted name like "Lion (3)"
     */
    public static String formatName(String speciesName, int number) {
        // Add null check to prevent NullPointerException
        if (speciesName == null) {
            return "Unknown (" + number + ")";
        }
        
        return speciesName.trim() + " (" + number + ")";
    }
    
    /**
     * Extracts the base species name from a formatted name.
     * Reverses the formatting done by formatName.
     * 
     * @param formattedName The formatted name like "Lion (3)"
     * @return The base species name like "Lion"
     */
    public static String extractBaseSpeciesName(String formattedName) {
        if (formattedName == null) {
            return "Unknown";
        }
        
        int openParenIndex = formattedName.indexOf('(');
        if (openParenIndex > 0) {
            return formattedName.substring(0, openParenIndex).trim();
        }
        return formattedName.trim();
    }
    
    /** 
    * Generates a new name for a cloned organism based on the parent's name.
    * This method extracts the base name from the parent, gets the next number
    * for this species type, and formats a new name accordingly.
    *
    * @param parentName The name of the parent organism
    * @return A new name for the cloned organism
    */
   public static String generateCloneName(String parentName) {
       // Extract base species name
       String baseName = extractBaseSpeciesName(parentName);
       // Get next number for this species
       int nextNumber = getNextNumber(baseName);
       // Format name with base and number
       return formatName(baseName, nextNumber);
   }
}