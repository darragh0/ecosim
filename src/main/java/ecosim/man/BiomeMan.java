package ecosim.man;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONObject;

import ecosim.common.io.FileIO;
import ecosim.enm.Biome;


public class BiomeMan {
    private final Biome biome;
    private final List<String> nativeAnimals;
    private final List<String> nativePlants;

    public BiomeMan(final Biome biome) {
        this.biome = biome;
        this.nativeAnimals = new ArrayList<>();
        this.nativePlants = new ArrayList<>();
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
        final Map<String, List<String>> lists = Map.ofEntries(
            Map.entry("ANIMALS", this.nativeAnimals),
            Map.entry("PLANTS", this.nativePlants));

        lists.forEach((name, lst) -> {
            if (!biomeData.has(name)) {
                LoggerMan.log(Level.SEVERE, "No {0} found for biome: {1}", name, biomeName);
                return;
            }

            final JSONArray jsonArr = biomeData.getJSONArray(name);
            for (int i = 0; i < jsonArr.length(); i++) {
                lst.add(jsonArr.getString(i));
            }

            LoggerMan.log(Level.INFO, "Loaded native {0} for biome: {1}", name, biomeName);
        });

        LoggerMan.log(Level.INFO, "Biome setup complete: {0}", biomeName);
    }

    public Biome getBiome() {
        return this.biome;
    }

    public List<String> getNativeAnimals() {
        return this.nativeAnimals;
    }

    public List<String> getNativePlants() {
        return this.nativePlants;
    }

}
