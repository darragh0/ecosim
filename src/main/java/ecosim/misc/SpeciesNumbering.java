package ecosim.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Central service for managing species numbering across the ecosystem.
 */
public class SpeciesNumbering {
    private static final Map<String, Integer> speciesCounter = new HashMap<>();
    
    /**
     * Gets the next number for a given species name.
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
}