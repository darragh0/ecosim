package ecosim.man;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import ecosim.enm.AnimalType;
import ecosim.enm.Biome;
import ecosim.enm.DesertAnimalType;
import ecosim.enm.DesertPlantType;
import ecosim.enm.GrasslandAnimalType;
import ecosim.enm.GrasslandPlantType;
import ecosim.enm.PlantType;


public class BiomeMan {

    private final Biome biome;
    private final List<AnimalType> nativeAnimals;
    private final List<PlantType> nativePlants;

    public BiomeMan(final Biome biome) {
        this.biome = biome;
        this.nativeAnimals = new ArrayList<>();
        this.nativePlants = new ArrayList<>();
        LoggerMan.log(Level.INFO, "Biome created: " + this.biome.name());
    }

    public void setupBiome() {
        final String biomeName = this.biome.name();
        LoggerMan.log(Level.INFO, "Setting up biome: {0}", biomeName);

        final List<AnimalType> animals = List.of(
            switch (this.biome) {
                case Biome.DESERT -> DesertAnimalType.values();
                case Biome.GRASSLAND -> GrasslandAnimalType.values();
            });

        final List<PlantType> plants = List.of(
            switch (this.biome) {
                case Biome.DESERT -> DesertPlantType.values();
                case Biome.GRASSLAND -> GrasslandPlantType.values();
            });

        this.nativeAnimals.addAll(animals);
        this.nativePlants.addAll(plants);

        LoggerMan.log(Level.INFO, "Biome setup complete: {0}", biomeName);
    }

    public Biome getBiome() {
        return this.biome;
    }

    public List<AnimalType> getNativeAnimals() {
        return this.nativeAnimals;
    }

    public List<PlantType> getNativePlants() {
        return this.nativePlants;
    }

}
