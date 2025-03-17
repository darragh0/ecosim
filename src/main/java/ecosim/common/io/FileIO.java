package ecosim.common.io;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONException;
import org.json.JSONObject;

import ecosim.game_engine.man.LoggerMan;
import ecosim.game_engine.misc.EcosystemConfig;

/**
 * Utility class for file input/output operations in the ecosystem simulation.
 * Provides methods for reading JSON files, creating directories, and parsing configuration.
 * @author Kabidoye-17, darragh0
 */
public final class FileIO {

    /**
     * Private constructor to prevent instantiation.
     * This class should only be used statically.
     */
    private FileIO() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Reads and parses a JSON file from the resources directory.
     * 
     * @param path The relative path to the JSON file within the resources/json directory
     * @return Optional containing the parsed JSONObject, or empty if file reading or parsing failed
     */
    public static Optional<JSONObject> readJSONFile(String path) {
        path = "src/main/resources/json/" + path;
        JSONObject json = null;

        try {
            final String content = new String(Files.readAllBytes(Paths.get(path)));
            json = new JSONObject(content);
        } catch (IOException e) {
            LoggerMan.log(Level.SEVERE, "Error reading JSON file: {0}", e.getMessage());
        } catch (JSONException e) {
            LoggerMan.log(Level.SEVERE, "Invalid JSON format: {0}", e.getMessage());
        }

        return Optional.ofNullable(json);
    }

    /**
     * Creates a directory at the specified path.
     * 
     * @param path The path for the directory to create
     * @return true if directory creation succeeded, false otherwise
     */
    public static boolean mkdir(final String path) {
        try {
            Files.createDirectories(Paths.get(path));
            return true;
        } catch (IOException e) {
            LoggerMan.log(Level.SEVERE, "Error creating directory: {0}", e.getMessage());
            return false;
        }
    }

    /**
     * Reads and parses the ecosystem configuration from JSON.
     * Validates that all required configuration keys are present.
     * 
     * @return Optional containing the EcosystemConfig if successful, empty otherwise
     */
    public static Optional<EcosystemConfig> parseEcosystemConfig() {
        final String[] keys = { "initialPlants", "initialAnimals", "maxCapacity", "maxDays", "hoursPerDay" };

        final Optional<JSONObject> jsonFile = FileIO.readJSONFile("ecosystem_config.json");
        if (jsonFile.isEmpty()) {
            LoggerMan.log(Level.SEVERE, "Could not parse ecosystem config");
            return null;
        }

        final JSONObject json = jsonFile.get();
        for (String key : keys) {
            boolean missing = false;
            if (!json.has(key)) {
                missing = true;
                LoggerMan.log(Level.SEVERE, "Missing key in JSON: {0}", key);
            }
            if (missing)
                return Optional.empty();
        }

        final int animals;
        final int plants;
        final int maxCapacity;
        final int maxDays;
        final int hoursPerDay;
        try {
            plants = json.getInt(keys[0]);
            animals = json.getInt(keys[1]);
            maxCapacity = json.getInt(keys[2]);
            maxDays = json.getInt(keys[3]);	
            hoursPerDay = json.getInt(keys[4]);
        } catch (JSONException e) {
            LoggerMan.log(Level.SEVERE, "Invalid JSON format: {0}", e.getMessage());
            return Optional.empty();
        }

        final EcosystemConfig cfg = new EcosystemConfig(animals, plants, maxCapacity, maxDays, hoursPerDay);
        return Optional.ofNullable(cfg);
    }

}
