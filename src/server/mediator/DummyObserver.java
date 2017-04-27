package server.mediator;

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
        Main.oHandler.adObserver(this);
        this.clients = new ArrayList<>();
    }

    public void addClient(ObjectOutputStream client){
        clients.add(client);
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int i=0;i<clients.size();i++){
            try {
                System.out.println("sending list");
                clients.get(i).writeObject(arg);
            } catch (IOException e) {
                //
            }
        }
    }
}
