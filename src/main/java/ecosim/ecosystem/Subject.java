package ecosim.ecosystem;

public interface Subject {
    void registerObservers();
    void unregisterObservers();
    void notifyObservers();
}
