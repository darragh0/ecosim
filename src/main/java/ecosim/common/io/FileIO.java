package ecosim.common.io;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONException;
import org.json.JSONObject;

import ecosim.LoggerManager;


public class FileIO {

    private FileIO() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static Optional<JSONObject> readJSONFile(final String path) {
        JSONObject json = null;

        try {
            final String content = new String(Files.readAllBytes(Paths.get(path)));
            json = new JSONObject(content);
        } catch (IOException e) {
            LoggerManager.log(Level.SEVERE, "Error reading JSON file: {0}", e.getMessage());
        } catch (JSONException e) {
            LoggerManager.log(Level.SEVERE, "Invalid JSON format: {0}", e.getMessage());
        }

        return Optional.ofNullable(json);
    }

    public static boolean mkdir(final String path) {
        try {
            Files.createDirectories(Paths.get(path));
            return true;
        } catch (IOException e) {
            LoggerManager.log(Level.SEVERE, "Error creating directory: {0}", e.getMessage());
            return false;
        }
    }

}
