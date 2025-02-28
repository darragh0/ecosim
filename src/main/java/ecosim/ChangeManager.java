package ecosim;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;

public interface ChangeManager {
    void register(Observable observable, Observer observer);
    void unregister(Observable observable, Observer observer);
    void notifyObservers(Observable subject);
}
