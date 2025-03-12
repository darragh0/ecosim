package ecosim.man;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONObject;

import static ecosim.common.Util.sub;
import static ecosim.common.Util.title;
import ecosim.common.io.FileIO;
import ecosim.enm.Biome;
import ecosim.organism.Organism;
import ecosim.organism.animal.Animal;
import ecosim.organism.plant.Plant;


public class BiomeMan {
    private final Biome biome;
    private final List<Class<? extends Animal>> animals;
    private final List<Class<? extends Plant>> plants;

    public BiomeMan(final Biome biome) {
        this.biome = biome;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        LoggerMan.log(Level.INFO, "Biome created: " + this.biome.name());
    }

    public void setupBiome() {
        final String biomeName = this.biome.name();
        LoggerMan.log(Level.INFO, "Setting up biome: {0}", biomeName);
        final Optional<JSONObject> jsonFile = FileIO.readJSONFile("src/main/resources/json/biome_natives.json");

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
        this.initList("ANIMALS", this.animals, biomeData);
        this.initList("PLANTS", this.plants, biomeData);

        LoggerMan.log(Level.INFO, "Biome setup complete: {0}", biomeName);
    }

    private <T extends Organism> void initList(String name, List<Class<? extends T>> lst, JSONObject biomeData) {
        final String biomeName = this.biome.name();

        if (!biomeData.has(name)) {
            LoggerMan.log(Level.SEVERE, "No {0} found for biome: {1}", name, biomeName);
            return;
        }

        final JSONArray jsonArr = biomeData.getJSONArray(name);
        for (int i = 0; i < jsonArr.length(); i++) {
            final String str = jsonArr.getString(i);
            final String pkg = sub(name.toLowerCase(), 0, -1);
            final String clsName = "ecosim.organism.%s.%s".formatted(pkg, title(str));

            try {
                @SuppressWarnings("unchecked")
                final Class<? extends T> cls = (Class<? extends T>) Class.forName(clsName);
                lst.add(cls);
            } catch (ClassNotFoundException ex) {
                LoggerMan.log(Level.SEVERE, "Could not find class: {0}", str);
            }
        }

        LoggerMan.log(Level.INFO, "Loaded native {0} for biome: {1}", biomeName);
    }

    public Biome getBiome() {
        return this.biome;
    }

    public List<Class<? extends Animal>> getAnimals() {
        return this.animals;
    }

    public List<Class<? extends Plant>> getPlants() {
        return this.plants;
    }

}
