package ecosim.attrs;

import ecosim.ChangeManager;
import ecosim.enm.Event;

public abstract class Observable {
    protected ChangeManager changeManager;

    public Observable(ChangeManager changeManager) {
        this.changeManager = changeManager;
    }

    public void attach(Observer observer) {
        changeManager.register(this, observer);
    }

    public void detach(Observer observer) {
        changeManager.unregister(this, observer);
    }

    public void notifyObservers() {
        changeManager.notifyObservers(this);
    }

    public abstract Event getCurrentState();
}
