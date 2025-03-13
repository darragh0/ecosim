package ecosim.common.io;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONException;
import org.json.JSONObject;

import ecosim.man.LoggerMan;
import ecosim.misc.EcosystemConfig;


public final class FileIO {

    private FileIO() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

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

    public static boolean mkdir(final String path) {
        try {
            Files.createDirectories(Paths.get(path));
            return true;
        } catch (IOException e) {
            LoggerMan.log(Level.SEVERE, "Error creating directory: {0}", e.getMessage());
            return false;
        }
    }

    public static Optional<EcosystemConfig> parseEcosystemConfig() {
        final String[] keys = { "initialPlants", "initialAnimals" };

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
        try {
            plants = json.getInt(keys[0]);
            animals = json.getInt(keys[1]);
        } catch (JSONException e) {
            LoggerMan.log(Level.SEVERE, "Invalid JSON format: {0}", e.getMessage());
            return Optional.empty();
        }

        final EcosystemConfig cfg = new EcosystemConfig(animals, plants);
        return Optional.ofNullable(cfg);
    }

}
