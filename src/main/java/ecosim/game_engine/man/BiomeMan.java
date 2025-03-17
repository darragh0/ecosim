package ecosim.game_engine.man;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONException;
import org.json.JSONObject;

import ecosim.common.io.FileIO;
import ecosim.game_engine.enm.ActivityType;
import ecosim.game_engine.enm.Biome;
import ecosim.game_engine.enm.Diet;
import ecosim.game_engine.enm.Size;
import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;


public class BiomeMan {

    private static final String JSON_FILE = "biome_natives.json";

    private final Biome biome;
    private final List<AnimalDescriptor> animals;
    private final List<PlantDescriptor> plants;

    public BiomeMan(final Biome biome) {
        this.biome = biome;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        LoggerMan.log(Level.INFO, "Biome created: " + this.biome.name());
    }

    public void setupBiome() {
        final String biomeName = this.biome.name();
        LoggerMan.log(Level.INFO, "Setting up biome: {0}", biomeName);
        final Optional<JSONObject> jsonFile = FileIO.readJSONFile(JSON_FILE);

        if (jsonFile.isEmpty()) {
            LoggerMan.log(Level.SEVERE, "Could not setup biome: {0}", biomeName);
            return;
        }

        final JSONObject json = jsonFile.get();
        if (!json.has(biomeName)) {
            LoggerMan.log(Level.SEVERE, "Biome not found: {0}", biomeName);
            return;
        }

        final JSONObject biomeData = json.getJSONObject(biomeName);

        // TODO: Make this a single method maybe (DRY principle and all that)
        this.initAnimalList(biomeData);
        this.initPlantList(biomeData);

        LoggerMan.log(Level.INFO, "Biome setup complete: {0}", biomeName);
    }

    private void initAnimalList(JSONObject biomeData) {
        final String key = "ANIMALS";
        
        if (!biomeData.has(key)) {
            LoggerMan.log(Level.SEVERE, "No animals found for biome: {0}", biome.name());
            return;
        }
        final JSONObject animalJson = biomeData.getJSONObject(key);

        for (final String animalKey : animalJson.keySet()) {
            final JSONObject animalData = animalJson.getJSONObject(animalKey);
            
            try {
                // Get the display name (either from JSON or use the key)
                final String displayName = animalData.optString("name", animalKey);
                
                // Create size enum
                final Size size;
                try {
                    size = Size.valueOf(animalData.getString("size"));
                } catch (IllegalArgumentException e) {
                    LoggerMan.log(Level.SEVERE, "Invalid size for animal: {0}", displayName);
                    continue;
                }
                
                // Create diet enum
                final Diet diet;
                try {
                    diet = Diet.valueOf(animalData.getString("diet"));
                } catch (IllegalArgumentException e) {
                    LoggerMan.log(Level.SEVERE, "Invalid diet for animal: {0}", displayName);
                    continue;
                }
                final ActivityType activityType;
                try {
                    activityType = ActivityType.valueOf(animalData.getString("activityType"));
                } catch (IllegalArgumentException e) {
                    LoggerMan.log(Level.SEVERE, "Invalid activity type for animal: {0}", displayName);
                    continue;
                }

                // Create animal descriptor without biome field
                final AnimalDescriptor animalDescriptor = new AnimalDescriptor(
                    displayName,
                    size,
                    diet,
                    activityType,
                    animalData.getBoolean("canHibernate"),
                    animalData.getString("sound"),
                    animalData.getString("symbol"));

                this.animals.add(animalDescriptor);
                LoggerMan.log(Level.INFO, "Loaded animal: {0}", displayName);
            } catch (JSONException ex) {
                LoggerMan.log(Level.SEVERE, "Error loading animal: {0}", ex.getMessage());
            }
        }

        LoggerMan.log(Level.INFO, "Loaded native {0} for biome: {1}", key, biome.name());
    }

    private void initPlantList(JSONObject biomeData) {
        final String key = "PLANTS";
        
        if (!biomeData.has(key)) {
            LoggerMan.log(Level.SEVERE, "No plants found for biome: {0}", biome.name());
            return;
        }

        final JSONObject plantJson = biomeData.getJSONObject(key);

        for (final String plantKey : plantJson.keySet()) {
            final JSONObject plantData = plantJson.getJSONObject(plantKey);
            
            try {
                // Get display name (either from JSON or use the key)
                final String displayName = plantData.optString("name", plantKey);
                
                // Create size enum
                final Size size;
                try {
                    size = Size.valueOf(plantData.optString("size", "MEDIUM"));
                } catch (IllegalArgumentException e) {
                    LoggerMan.log(Level.SEVERE, "Invalid size for plant: {0}", displayName);
                    continue;
                }

                final PlantDescriptor plantDescriptor = new PlantDescriptor(
                    displayName,
                    size,
                    plantData.getString("symbol"));

                this.plants.add(plantDescriptor);
                LoggerMan.log(Level.INFO, "Loaded plant: {0}", displayName);
            } catch (JSONException ex) {
                LoggerMan.log(Level.SEVERE, "Error loading plant: {0}", ex.getMessage());
            }
        }

        LoggerMan.log(Level.INFO, "Loaded native {0} for biome: {1}", key, biome.name());
    }

    public Biome getBiome() {
        return this.biome;
    }

    public List<AnimalDescriptor> getAnimals() {
        return this.animals;
    }

    public List<PlantDescriptor> getPlants() {
        return this.plants;
    }

}
