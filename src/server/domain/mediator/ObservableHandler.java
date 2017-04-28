package server.domain.mediator;

import server.domain.model.ProxyTripList;
import server.domain.model.TripList;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Marek on 24-Apr-17.
 */
public class ObservableHandler extends Observable {

    public ObservableHandler() {

    }

    public void adObserver(Observer ob){
        super.addObserver(ob);
    }

    public void notify(ProxyTripList trips){
        super.setChanged();
        super.notifyObservers(trips);
    }
}
