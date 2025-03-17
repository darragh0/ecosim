package ecosim.game_engine.man;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;

/**
 * Implementation of the ChangeMan interface using the Singleton pattern.
 * Manages observers and notifies them of changes to observed objects.
 * @author Kabidoye-17
 */
public class SimpleChangeMan implements ChangeMan {

    private static SimpleChangeMan instance;
    private final Map<Observable, List<Observer>> observableObserverMap = new HashMap<>();

    /**
     * Private constructor to enforce Singleton pattern.
     */
    private SimpleChangeMan() {}

    /**
     * Gets the single instance of SimpleChangeMan.
     * 
     * @return The SimpleChangeMan singleton instance
     */
    public static SimpleChangeMan getInstance() {
        if (instance == null) {
            instance = new SimpleChangeMan();
        }
        return instance;
    }

    /**
     * Registers an observer to be notified of changes to an observable.
     * 
     * @param observable The object being observed
     * @param observer The observer to be notified
     */
    @Override
    public void register(Observable observable, Observer observer) {
        List<Observer> observers = observableObserverMap.computeIfAbsent(observable, _ -> new ArrayList<>());
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Unregisters an observer from an observable.
     * 
     * @param observable The object being observed
     * @param observer The observer to be removed
     */
    @Override
    public void unregister(Observable observable, Observer observer) {
        List<Observer> observers = observableObserverMap.get(observable);
        if (observers != null) {
            observers.remove(observer);
            if (observers.isEmpty()) {
                observableObserverMap.remove(observable);
            }
        }
    }

    /**
     * Notifies all observers of a change in the observable.
     * 
     * @param observable The observable that has changed
     */
    @Override
    public void notifyObservers(Observable observable) {
        List<Observer> observers = observableObserverMap.get(observable);
        if (observers != null) {
            for (Observer obs : observers) {
                obs.update(observable);
            }
        }
    }

}
