package ecosim.man;


import static ecosim.common.Util.sub;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONObject;

import ecosim.common.io.FileIO;
import ecosim.enm.ActivityType;
import ecosim.enm.Biome;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.misc.AnimalDescriptor;
import ecosim.misc.PlantDescriptor;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;


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
        final String biomeName = this.biome.name();

        if (!biomeData.has(key)) {
            LoggerMan.log(Level.SEVERE, "No animals found for biome: {1}", key, biomeName);
            return;
        }

        final JSONObject animalJson = biomeData.getJSONObject(key);

        for (final String animalName : animalJson.keySet()) {
            final JSONObject animalData = animalJson.getJSONObject(animalName);
            final String pkg = sub(key.toLowerCase(), 0, -1);
            final String clsName = "ecosim.organism.%s.concrete.%s.%s".formatted(
                pkg,
                biomeName.toLowerCase(),
                animalName);

            try {
                @SuppressWarnings("unchecked")
                final Class<? extends Animal> cls = (Class<? extends Animal>) Class.forName(clsName);
                final Size size;
                try {
                    size = Size.valueOf(animalData.getString("size"));
                } catch (IllegalArgumentException e) {
                    LoggerMan.log(Level.SEVERE, "Invalid size for animal: {0}", animalName);
                    continue;
                }
                final Diet diet;
                try {
                    diet = Diet.valueOf(animalData.getString("diet"));
                } catch (IllegalArgumentException e) {
                    LoggerMan.log(Level.SEVERE, "Invalid diet for animal: {0}", animalName);
                    continue;
                }
                final ActivityType activityType;
                try {
                    activityType = ActivityType.valueOf(animalData.getString("activityType"));
                } catch (IllegalArgumentException e) {
                    LoggerMan.log(Level.SEVERE, "Invalid activity type for animal: {0}", animalName);
                    continue;
                }

                final AnimalDescriptor animalDescriptor = new AnimalDescriptor(
                    cls,
                    size,
                    diet,
                    activityType,
                    animalData.getBoolean("canHibernate"),
                    animalData.getString("sound"),
                    animalData.getString("symbol"));

                this.animals.add(animalDescriptor);
            } catch (ClassNotFoundException ex) {
                LoggerMan.log(Level.SEVERE, "Could not find animal class: {0}", clsName);
                continue;
            }

            LoggerMan.log(Level.INFO, "Loaded animal class: {0}", clsName);

        }

        LoggerMan.log(Level.INFO, "Loaded native {0} for biome: {1}", key, biomeName);
    }

    private void initPlantList(JSONObject biomeData) {
        final String key = "PLANTS";
        final String biomeName = this.biome.name();

        if (!biomeData.has(key)) {
            LoggerMan.log(Level.SEVERE, "No plants found for biome: {1}", key, biomeName);
            return;
        }

        final JSONObject plantJson = biomeData.getJSONObject(key);

        for (final String plantName : plantJson.keySet()) {
            final JSONObject plantData = plantJson.getJSONObject(plantName);
            final String pkg = sub(key.toLowerCase(), 0, -1);
            final String clsName = "ecosim.organism.%s.concrete.%s.%s".formatted(
                pkg,
                biomeName.toLowerCase(),
                plantName);

            try {
                @SuppressWarnings("unchecked")
                final Class<? extends Plant> cls = (Class<? extends Plant>) Class.forName(clsName);
                final Size size;
                try {
                    size = Size.valueOf(plantData.getString("size"));
                } catch (IllegalArgumentException e) {
                    LoggerMan.log(Level.SEVERE, "Invalid size for plant: {0}", plantName);
                    continue;
                }
                final PlantDescriptor plantDescriptor = new PlantDescriptor(
                    cls,
                    size,
                    plantData.getString("symbol"));

                this.plants.add(plantDescriptor);
            } catch (ClassNotFoundException ex) {
                LoggerMan.log(Level.SEVERE, "Could not find plant class: {0}", clsName);
                continue;
            }

            LoggerMan.log(Level.INFO, "Loaded plant class: {0}", clsName);
        }

        LoggerMan.log(Level.INFO, "Loaded native {0} for biome: {1}", key, biomeName);
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
