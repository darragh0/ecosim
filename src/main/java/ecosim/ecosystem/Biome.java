package ecosim.ecosystem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Biome {
    private final String name;
    private ArrayList<String> nativeAnimals;
    private ArrayList<String> nativePlants;


    public Biome(String name) {
        this.name = name;
    }

    public void loadNativeAnimals() {
        // TODO: implement loading native animals from a json based on biome name
    }

    public void loadNativePlants() {
        // TODO: implement loading native plants from a json based on biome name
    }

    public void setupBiome(){
        loadNativeAnimals();
        loadNativePlants();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getNativeAnimals() {
        return nativeAnimals;
    }

    public ArrayList<String> getNativePlants() {
        return nativePlants;
    }
}
