package server.domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by aykon on 23-Apr-17.
 */
public class ServerCommunication implements Runnable {
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    public ServerCommunication(Socket clientSocket, DummyObserver observer)
            throws IOException
    {
        outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        inFromClient = new ObjectInputStream(clientSocket.getInputStream());
        observer.addClient(outToClient);
    }

    public void run() {
        try {
            outToClient.writeObject(DataHandler.getInstance().getTrips());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
