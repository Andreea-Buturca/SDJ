package server.domain.mediator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.Main;
import server.domain.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class that manages data and files.
 *
 * @author IT-1Y-A16 Group 1
 */

public class DataHandler implements Serializable {

    public static final char CLASSIC = 'c';
    public static final char LUXURY = 'l';
    public static final char PARTY = 'p';
    public static final char MINI = 'm';
    private static DataHandler dataHandler;
    private ProxyTripList trips;
    private BusList busList;
    private ChauffeurList chauffeurList;
    private CustomerList customerList;
    private ReservationList reservationList;
    private DestinationList destinationList;


    private DataHandler() {

    }

    public static DataHandler getInstance() {
        if (dataHandler == null) {
            dataHandler = new DataHandler();
        }
        System.out.println(dataHandler.hashCode());
        return dataHandler;
    }

    /**
     * @return main ProxyTripList
     */

    public ProxyTripList getTrips() {
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

    public void removeFromBuslist(Bus bus) {
        busList.removeBus(bus);
    }

    public ObservableList getObservableListOfBuses() {
        ObservableList<Bus> items = FXCollections.observableArrayList();
        for (Bus bus : busList.getArrayBuses()) {
            items.add(bus);
        }
        return items;
    }

    public void addBus(String regplate, int seats, char type) {
        switch (type) {
            case PARTY:
                busList.add(new PartyBus(regplate, seats));
                break;
            case LUXURY:
                busList.add(new LuxuryBus(regplate, seats));
                break;
            case MINI:
                busList.add(new MiniBus(regplate, seats));
                break;
            default:
                busList.add(new ClassicBus(regplate, seats));
                break;
        }
    }

    public ChauffeurList getChauffeurList() {
        return chauffeurList;
    }

    /**
     * @return main CustomerList
     */

    public ObservableList getObservableListOfChauffeurs() {
        ObservableList<Chauffeur> items = FXCollections.observableArrayList();
        for (Chauffeur chauffeur : chauffeurList.getArrayChauffeur()) {
            items.add(chauffeur);
        }
        return items;
    }

    public void removeFromChauffeurlist(Chauffeur chauffeur) {
        chauffeurList.removeChauffeur(chauffeur);
    }

    public void addChauffeur(String name, String address, String email, String phone, LocalDate bday, int id,
                             boolean isVikar, boolean shortdistance, boolean mediumdistance, boolean longdistance,
                             boolean classic, boolean party, boolean luxury, boolean mini) {

        Chauffeur c = new Chauffeur(name, address, email, phone, bday, id, isVikar);

        if (!c.isVikar()) {
            if (shortdistance) {
                c.setPreferredShortDistance(400);
            }
            if (mediumdistance) {
                c.setPreferredMediumDistance(1000);
            }
            if (longdistance) {
                c.setPreferredLongDistance(2500);
            }
            if (classic) {
                c.setPreferredBusType("Classic Bus");
            }
            if (luxury) {
                c.setPreferredBusType("Luxury Bus");
            }
            if (party) {
                c.setPreferredBusType("Party Bus");
            }
            if (mini) {
                c.setPreferredBusType("Mini Bus");
            }
        }
        chauffeurList.add(c);
    }

    public ObservableList getSortedObsrvableTriplist() {
        ProxyTripList t = new ProxyTripList();
        for (int i = 0; i < trips.getSize(); i++) {
            t.add(trips.get(i));
        }
        t.sort();
        ObservableList<Trip> data = FXCollections.observableArrayList();
        for (int i = 0; i < t.getSize(); i++) {
            if (t.getArrayTrip().get(i).getDateStart().isEqual(LocalDate.now())) data.add(trips.get(i));
            if (t.getArrayTrip().get(i).getDateStart().isAfter(LocalDate.now())) data.add(trips.get(i));
        }
        return data;
    }

    public ArrayList<Destination> getArrayOfDestinations() {
        return destinationList.getArrayDestination();
    }

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
        trips = new ProxyTripList();
        busList = new BusList();
        chauffeurList = new ChauffeurList();
        customerList = new CustomerList();
        reservationList = new ReservationList();
        destinationList = new DestinationList();
    }

    /**
     * Creates file to save data in.
     */

    public void save() {

        String filename = "mainData.bin";
        saveFile(filename);

    }

    /**
     * Creates back-up file.
     */

    public void backUp() {

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
            trips = (ProxyTripList) in.readObject();
            busList = (BusList) in.readObject();
            chauffeurList = (ChauffeurList) in.readObject();
            customerList = (CustomerList) in.readObject();
            reservationList = (ReservationList) in.readObject();
            destinationList = (DestinationList) in.readObject();
        } catch (ClassCastException | IOException | ClassNotFoundException e) {
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

    private boolean validateEmptyField(String field) {
        return !field.isEmpty();

    }

    private boolean validateNumberField(String field) {
        return field.matches("[0-9]+");

    }

    protected boolean validateTimeField(String field) {
        return field.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");

    }

    public ObservableList getObservableChauffeursLoadList(String fieldDistance, String busType, LocalDate startDatePicker, LocalDate endDatePicker, String fieldStartTime, String fieldEndTime) {
        ChauffeurList chauffeurs = chauffeurList.copy();
        chauffeurs.removeAllVicars();

        if (validateEmptyField(fieldDistance) && validateNumberField(fieldDistance)) {
            chauffeurs = chauffeurs.getAllByPrefferedDistance(Integer.parseInt(fieldDistance));
        }
        chauffeurs = chauffeurs.getAllByPrefferedBus(busType);

        for (Chauffeur chauffeur : DataHandler.getInstance().getChauffeurList().getAllVicars().getArrayChauffeur()) {
            chauffeurs.add(chauffeur);
        }

        if (startDatePicker != null && endDatePicker != null && validateTimeField(fieldStartTime) && validateTimeField(fieldEndTime)) {
            String[] lineToken = fieldStartTime.split(":");
            int hours = Integer.parseInt(lineToken[0]);
            int minutes = Integer.parseInt(lineToken[1]);
            Date dateStart = new Date(startDatePicker.getYear() - 1900, startDatePicker.getMonthValue(), startDatePicker.getDayOfMonth(), hours, minutes);
            lineToken = fieldEndTime.split(":");
            hours = Integer.parseInt(lineToken[0]);
            minutes = Integer.parseInt(lineToken[1]);
            Date dateEnd = new Date(endDatePicker.getYear() - 1900, endDatePicker.getMonthValue(), endDatePicker.getDayOfMonth(), hours, minutes);
            chauffeurs = chauffeurs.getAvailable(dateStart, dateEnd);
        }
        ObservableList<Chauffeur> items = FXCollections.observableArrayList();
        if (chauffeurs.getSize() != 0) {
            items.addAll(chauffeurs.getArrayChauffeur());
        }
        return items;
    }

    public ObservableList<Bus> loadBuses(String busType, LocalDate startDatePicker, LocalDate endDatePicker, String fieldStartTime, String fieldEndTime) {
        BusList buses;

        if (busType.equals("Mini Bus"))
            buses = new BusList(busList.searchByType("server.domain.model.MiniBus"));
        else if (busType.equals("Party Bus"))
            buses = new BusList(busList.searchByType("server.domain.model.PartyBus"));
        else if (busType.equals("Luxury Bus"))
            buses = new BusList(busList.searchByType("server.domain.model.LuxuryBus"));
        else buses = new BusList(busList.searchByType("server.domain.model.ClassicBus"));

        if (startDatePicker != null && endDatePicker != null && validateTimeField(fieldStartTime) && validateTimeField(fieldEndTime)) {
            String[] lineToken = fieldStartTime.split(":");
            int hours = Integer.parseInt(lineToken[0]);
            int minutes = Integer.parseInt(lineToken[1]);
            Date dateStart = new Date(startDatePicker.getYear() - 1900, startDatePicker.getMonthValue(), startDatePicker.getDayOfMonth(), hours, minutes);
            lineToken = fieldEndTime.split(":");
            hours = Integer.parseInt(lineToken[0]);
            minutes = Integer.parseInt(lineToken[1]);
            Date dateEnd = new Date(endDatePicker.getYear() - 1900, endDatePicker.getMonthValue(), endDatePicker.getDayOfMonth(), hours, minutes);
            buses = buses.getAvailable(dateStart, dateEnd);
        }

        ObservableList<Bus> items = FXCollections.observableArrayList();
        items.addAll(buses.getArrayBuses());
        return items;
    }

    public Destination getDestinationByName(String name) {
        return destinationList.findByName(name);
    }

    public void addDestination(Destination destination) {
        destinationList.add(destination);
    }

    public void addTrip(Bus bus, Chauffeur chauffeur, Destination pickUp, Destination destination, int distance, LocalDate startDatePicker, String fieldStartTime, LocalDate endDatePicker, String fieldEndTime, int price, boolean food, boolean accommodation, boolean ticket) {
        Trip trip = new Trip(bus, chauffeur, pickUp, destination, distance, startDatePicker, fieldStartTime, endDatePicker, fieldEndTime, price);

        if (food) {
            trip.setFood(true);
        }
        if (accommodation) {
            trip.setAccommodation(true);
        }
        if (ticket) {
            trip.setTickets(true);
        }
        trips.add(trip);

        Main.oHandler.notify(trips);
    }

    public void getInDates(LocalDate[] dates, ObjectOutputStream client) {
        System.out.println(dates[0]);
        ProxyTripList result = new ProxyTripList();
        ProxyTripList trips = DataHandler.getInstance().getTrips();
        for (int i = 0; i < trips.getSize(); i++) {
            if ((
                    trips.getArrayTrip().get(i).getDateStart().isEqual(dates[0]) ||
                            trips.getArrayTrip().get(i).getDateStart().isAfter(dates[0])
            ) && (
                    trips.getArrayTrip().get(i).getDateStart().isEqual(dates[1]) ||
                            trips.getArrayTrip().get(i).getDateStart().isBefore(dates[1])
            )) {
                result.add(trips.get(i));
            }
        }
        try {
            client.writeObject(result);
        } catch (IOException e) {
            //
        }
    }

}

