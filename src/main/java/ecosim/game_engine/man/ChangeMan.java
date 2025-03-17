package ecosim.game_engine.man;


import ecosim.attrs.Observable;
import ecosim.attrs.Observer;


public interface ChangeMan {
    void register(Observable observable, Observer observer);

    void unregister(Observable observable, Observer observer);

    void notifyObservers(Observable subject);

}
