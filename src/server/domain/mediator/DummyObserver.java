package server.domain.mediator;

import server.Main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Marek on 26-Apr-17.
 */
public class DummyObserver implements Observer {

    private ArrayList<ObjectOutputStream> clients;

    public DummyObserver() {
        Main.oHandler.addObserver(this);
        this.clients = new ArrayList<>();
    }

    public void addClient(ObjectOutputStream client){
        clients.add(client);
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int i=0;i<clients.size();i++){
            try {
                clients.get(i).writeObject(arg);
                clients.get(i).reset();
            } catch (IOException e) {
                //
            }
        }
    }
}
