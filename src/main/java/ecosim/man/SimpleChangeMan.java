package ecosim.man;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;


public class SimpleChangeMan implements ChangeMan {

    private static SimpleChangeMan instance;
    private final Map<Observable, List<Observer>> observableObserverMap = new HashMap<>();

    private SimpleChangeMan() {}

    public static SimpleChangeMan getInstance() {
        if (instance == null) {
            instance = new SimpleChangeMan();
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
