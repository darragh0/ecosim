package ecosim;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleChangeManager implements ChangeManager {

    private final Map<Observable, List<Observer>> subjectObserverMap = new HashMap<>();

    @Override
    public void register(Observable observable, Observer observer) {
        List<Observer> observers = subjectObserverMap.computeIfAbsent(observable, k -> new ArrayList<>());
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unregister(Observable observable, Observer observer) {
        List<Observer> observers = subjectObserverMap.get(observable);
        if (observers != null) {
            observers.remove(observer);
            if (observers.isEmpty()) {
                subjectObserverMap.remove(observable);
            }
        }
    }


    @Override
    public void notifyObservers(Observable subject, Event event) {
        List<Observer> observers = subjectObserverMap.get(subject);
        if (observers != null) {
            for (Observer obs : observers) {
                obs.update(event);
            }
        }
    }
}
