package client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by aykon on 23-Apr-17.
 */
public class ConnectionController implements Runnable{

    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private Socket socket;

    private static final String HOST = "localhost";
    private static final int PORT = 6666;

    public ConnectionController() throws IOException
    {
        try
        {
            socket = new Socket(HOST, PORT);
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());
            //ClientReceiver reciever = new ClientReceiver(inFromServer, model);
            //new Thread(reciever, "Reciever").start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void run()
    {
        try
        {
            while (true)
            {

                System.out.println("Reciever: ");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
