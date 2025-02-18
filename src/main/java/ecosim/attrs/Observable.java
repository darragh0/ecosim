package ecosim.attrs;


public interface Observable {
    void registerObservers();

    void unregisterObservers();

    void notifyObservers();

}
