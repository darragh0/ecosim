package ecosim.attrs;

/**
 * Represents an object that can be observed for changes.
 * @author darragh0
 */

import ecosim.game_engine.enm.Event;
import ecosim.game_engine.man.ChangeMan;


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
