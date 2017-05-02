package client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

/**
 * Created by aykon on 23-Apr-17.
 */
public class ConnectionController {

    private static final String HOST = "localhost";
    private static final int PORT = 6666;
    private ObjectOutputStream outToServer;

    public ConnectionController() throws IOException {
        try {
            Socket socket = new Socket(HOST, PORT);
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
            ClientReceiver reciever = new ClientReceiver(inFromServer);
            new Thread(reciever, "Reciever").start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendDatesToServer(LocalDate from, LocalDate to) {
        LocalDate[] dates = {from, to};
        try {
            outToServer.writeObject(dates);
        } catch (IOException e) {
            //
        }
    }
}
