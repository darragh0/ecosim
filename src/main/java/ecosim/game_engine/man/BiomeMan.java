package ecosim.game_engine.man;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONObject;

import ecosim.common.io.FileIO;
import ecosim.game_engine.enm.ActivityType;
import ecosim.game_engine.enm.Biome;
import ecosim.game_engine.enm.Diet;
import ecosim.game_engine.enm.Size;
import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.interceptor.Try;


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

            Try.exec(() -> {
                final String displayName = animalData.optString("name", animalKey);

                final Size size = Try.exec(
                    () -> Size.valueOf(animalData.getString("size")),
                    1,
                    "Invalid size for animal: %s",
                    displayName);

                final Diet diet = Try.exec(
                    () -> Diet.valueOf(animalData.getString("diet")),
                    1,
                    "Invalid diet for animal: %s",
                    displayName);

                final ActivityType activityType = Try.exec(
                    () -> ActivityType.valueOf(animalData.getString("activityType")),
                    1,
                    "Invalid activity type for animal: %s",
                    displayName);

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
            },
                1,
                "Error loading animal: %s",
                animalKey);
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

            Try.exec(() -> {
                final String displayName = plantData.optString("name", plantKey);

                final Size size = Try.exec(
                    () -> Size.valueOf(plantData.optString("size", "MEDIUM")),
                    1,
                    "Invalid size for plant: %s",
                    displayName);

                final PlantDescriptor plantDescriptor = new PlantDescriptor(
                    displayName,
                    size,
                    plantData.getString("symbol"));

                this.plants.add(plantDescriptor);
                LoggerMan.log(Level.INFO, "Loaded plant: {0}", displayName);
            },
                1,
                "Error loading plant: %s",
                plantKey);
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
