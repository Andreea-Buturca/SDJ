package client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDate;

/**
 * Created by aykon on 23-Apr-17.
 */
public class ConnectionController implements Runnable{

    private static final String HOST = "localhost";
    private static final int PORT = 6666;
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private Socket socket;

    public ConnectionController() throws IOException
        {
        try
        {
            socket = new Socket(HOST, PORT);
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());
            ClientReceiver reciever = new ClientReceiver(inFromServer, outToServer);
            new Thread(reciever, "Reciever").start();
        }
        catch (Exception e)
        {
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

    public void run()
    {
        /*try
        {
            while (true)
            {
                System.out.println("Reciever: ");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
    }
}
