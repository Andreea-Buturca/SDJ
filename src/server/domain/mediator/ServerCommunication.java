package server.domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

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
            outToClient.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                LocalDate[] dates = (LocalDate[]) inFromClient.readObject();
                DataHandler.getInstance().getInDates(dates, outToClient);
                inFromClient.reset();
                System.out.println(dates.length);
            } catch (IOException e) {
                //
            } catch (ClassNotFoundException e) {
                //
            }
        }

    }

}
