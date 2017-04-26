package server.mediator;

import server.model.*;

import java.io.*;

/**
 * Class that manages data and files.
 *
 * @author IT-1Y-A16 Group 1
 */

public class DataHandler implements Serializable {

    private TripList trips;
    private BusList busList;
    private ChauffeurList chauffeurList;
    private CustomerList customerList;
    private ReservationList reservationList;
    private DestinationList destinationList;
    private static DataHandler dataHandler;

    private DataHandler() {

    }
public static DataHandler getDataHandler()
{
    if(dataHandler==null)
    {
        dataHandler=new DataHandler();
    }
    return dataHandler;
}
    /**
     * @return main TripList
     */

    public TripList getTrips() {
        return trips;
    }

    /**
     * @return main BusList
     */

    public BusList getBusList() {
        return busList;
    }

    /**
     * @return main ChauffeurList
     */

    public ChauffeurList getChauffeurList() {
        return chauffeurList;
    }

    /**
     * @return main CustomerList
     */

    public CustomerList getCustomerList() {
        return customerList;
    }

    /**
     * @return main ReservationList
     */

    public ReservationList getReservationList() {
        return reservationList;
    }

    /**
     * @return main DestinationList
     */

    public DestinationList getDestinationList() {
        return destinationList;
    }

    public void testCreate() {
        trips = new TripList();
        busList = new BusList();
        chauffeurList = new ChauffeurList();
        customerList = new CustomerList();
        reservationList = new ReservationList();
        destinationList = new DestinationList();
    }

    /**
     * Creates file to save data in.
     */

    public  void save() {

        String filename = "mainData.bin";
        saveFile(filename);

    }

    /**
     * Creates back-up file.
     */

    public  void backUp() {

        String filename = "mainDataBackUp.bin";
        saveFile(filename);

    }

    /***
     * Loads all data from main file.
     */

    public void load() {
        String filename = "mainData.bin";
        ObjectInputStream in = null;
        try {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            trips = (TripList) in.readObject();
            busList = (BusList) in.readObject();
            chauffeurList = (ChauffeurList) in.readObject();
            customerList = (CustomerList) in.readObject();
            reservationList = (ReservationList) in.readObject();
            destinationList = (DestinationList) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            testCreate();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves all data into file with given filename.
     *
     * @param filename name of file to save into
     */

    private void saveFile(String filename) {
        ObjectOutputStream out = null;
        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(trips);
            out.writeObject(busList);
            out.writeObject(chauffeurList);
            out.writeObject(customerList);
            out.writeObject(reservationList);
            out.writeObject(destinationList);
        } catch (IOException e) {
            System.out.println("Exception: " + filename);
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

