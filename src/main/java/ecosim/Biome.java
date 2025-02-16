package ecosim;
import java.util.ArrayList;
import java.util.List;

public class Biome {
    private final String name;
    private List<String> nativeAnimals;
    private List<String> nativePlants;


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
        this.loadNativeAnimals();
        this.loadNativePlants();
    }

    public String getName() {
        return this.name;
    }

    public List<String> getNativeAnimals() {
        return this.nativeAnimals;
    }

    public List<String> getNativePlants() {
        return this.nativePlants;
    }
}
