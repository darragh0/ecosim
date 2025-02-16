package ecosim;

public interface Observable {
    void registerObservers();
    void unregisterObservers();
    void notifyObservers();
}
