package ecosim.attrs;


import ecosim.enm.Event;
import ecosim.man.ChangeMan;


public abstract class Observable {
    protected ChangeMan changeManager;

    public Observable(ChangeMan changeManager) {
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
