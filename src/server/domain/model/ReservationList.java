package server.domain.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class which represents a list of reservations.
 *
 * @author IT-1Y-A16 Group 1
 */

public class ReservationList implements Serializable {

    private ArrayList<Reservation> reservations;

    /**
     * Constructs a list of reservations.
     */

    public ReservationList() {
        this.reservations = new ArrayList<>();
    }

    /**
     * Adds a given reservation to the list.
     *
     * @param reservation reservation to add
     */

    public void add(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * @return arraylist of all reservations in the list
     */

    public ArrayList<Reservation> getArrayReservation() {
        return this.reservations;
    }

    /**
     * Removes given reservation from the list.
     *
     * @param reservation reservation to remove
     */

    public void remove(Reservation reservation) {
        reservations.remove(reservation);
    }

    /**
     * @return size of reservation list
     */

    public int getSize() {
        return reservations.size();
    }

    /**
     * Finds reservation at the given index.
     *
     * @param index index to look at
     * @return reservation at given index
     */

    public Reservation getReservation(int index) {
        return reservations.get(index);
    }

    /**
     * @return reservation converted to String
     */

    public String toString() {
        String s = "";
        for (int i = 0; i < reservations.size(); i++) {
            s += reservations.get(i);
            if (i < reservations.size() - 1)
                s += "\n";
        }
        return s;
    }
}
