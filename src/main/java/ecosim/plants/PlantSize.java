package ecosim.plants;

public enum PlantSize {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int biteCapacity;

    PlantSize(int biteCapacity) {
        this.biteCapacity = biteCapacity;
    }

    public int getBiteCapacity() {
        return biteCapacity;
    }
}
