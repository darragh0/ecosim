package ecosim;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleChangeManager implements ChangeManager {

    private static SimpleChangeManager instance;
    private final Map<Observable, List<Observer>> observableObserverMap = new HashMap<>();

    private SimpleChangeManager() { }

    public static SimpleChangeManager getInstance() {
        if (instance == null) {
            instance = new SimpleChangeManager();
        }
        return instance;
    }

    @Override
    public void register(Observable observable, Observer observer) {
        List<Observer> observers = observableObserverMap.computeIfAbsent(observable, k -> new ArrayList<>());
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

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
