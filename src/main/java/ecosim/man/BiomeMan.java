package ecosim.man;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ecosim.enm.Biome;


public class BiomeMan {
    private final Biome biome;
    private final List<String> nativeAnimals;
    private final List<String> nativePlants;
    private static Logger LOGGER;

    public BiomeMan(String biomeName) {
        this.biome = Biome.valueOf(biomeName.toUpperCase());
        this.nativeAnimals = new ArrayList<>();
        this.nativePlants = new ArrayList<>();
        LOGGER = LoggerMan.getLogger();
        LOGGER.info("Biome created: " + this.biome.getName());
    }

    public String getBiomeName() {
        return this.biome.getName();
    }

    public void loadNativeAnimals() {
        String biome = this.getBiomeName().toUpperCase();
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/json/biome_natives.json")));
            JSONObject json = new JSONObject(content);
            if (json.has(biome)) {
                JSONObject biomeData = json.getJSONObject(biome);
                if (biomeData.has("ANIMALS")) {
                    JSONArray animalsArray = biomeData.getJSONArray("ANIMALS");
                    for (int i = 0; i < animalsArray.length(); i++) {
                        this.nativeAnimals.add(animalsArray.getString(i));
                    }
                    LOGGER.info("Loaded native animals for " + biome);
                }
            } else {
                LOGGER.warning("Biome not found: " + biome);
            }
        } catch (IOException e) {
            LOGGER.severe("Error reading biome file: " + e.getMessage());
        } catch (JSONException e) {
            LOGGER.severe("Invalid JSON format: " + e.getMessage());
        }
    }

    public void loadNativePlants() {
        String biome = this.getBiomeName().toUpperCase();
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/json/biome_natives.json")));
            JSONObject json = new JSONObject(content);
            if (json.has(biome)) {
                JSONObject biomeData = json.getJSONObject(biome);
                if (biomeData.has("PLANTS")) {
                    JSONArray plantsArray = biomeData.getJSONArray("PLANTS");
                    for (int i = 0; i < plantsArray.length(); i++) {
                        this.nativePlants.add(plantsArray.getString(i));
                    }
                    LOGGER.info("Loaded native plants for " + biome);
                }
            } else {
                LOGGER.warning("Biome not found: " + biome);
            }
        } catch (IOException e) {
            LOGGER.severe("Error reading biome file: " + e.getMessage());
        } catch (JSONException e) {
            LOGGER.severe("Invalid JSON format: " + e.getMessage());
        }
    }

    public void setupBiome() {
        LOGGER.info("Setting up biome: " + this.getBiomeName());
        this.loadNativeAnimals();
        this.loadNativePlants();
    }

    public List<String> getNativeAnimals() {
        return this.nativeAnimals;
    }

    public List<String> getNativePlants() {
        return this.nativePlants;
    }

}
