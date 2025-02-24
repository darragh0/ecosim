package ecosim.enm;

public enum Biome {
    DESERT("Desert"),
    GRASSLAND("Grassland");

    private final String name;

    Biome(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
