package server.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class which represents a list of passengers.
 *
 * @author IT-1Y-A16 Group 1
 */

public class PassengerList implements Serializable {

    private ArrayList<Passenger> passengers = new ArrayList<>();

    /**
     * Constructs a passenger list.
     */

    public PassengerList() {
    }

    /**
     * Adds a given passenger to the list.
     *
     * @param passenger passenger to add
     */

    public void add(Passenger passenger) {
        this.passengers.add(passenger);
    }

    /**
     * @return arraylist of all passengers in list
     */

    public ArrayList<Passenger> getArrayPassenger() {
        return passengers;
    }

    /**
     * Removes given passenger from the list.
     *
     * @param passenger passenger to remove
     */

    public void removePassenger(Passenger passenger) {
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).equals(passenger))
                passengers.remove(passenger);
        }
    }

    /**
     * @return list converted to String
     */

    public String toString() {
        String s = "";
        for (int i = 0; i < passengers.size(); i++) {
            s += passengers.get(i);
            if (i < passengers.size() - 1)
                s += "\n";
        }
        return s;
    }
}
