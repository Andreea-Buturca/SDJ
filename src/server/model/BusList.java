package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class which represents a list of buses.
 *
 * @author IT-1Y-A16 Group 1
 */

public class BusList implements Serializable {

    private ArrayList<Bus> buses = new ArrayList<>();

    /**
     * Constructs a list of buses.
     */

    public BusList() {
    }

    /**
     * Constructs a list of buses with given list.
     *
     * @param buses arraylist of buses to create list with
     */

    public BusList(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    /**
     * Adds a given bus to the list.
     *
     * @param bus bus to add
     */

    public void add(Bus bus) {
        buses.add(bus);
    }

    /**
     * @return arraylist of all buses in the list
     */

    public ArrayList<Bus> getArrayBuses() {
        return buses;
    }

    /**
     * Finds bus at the given index.
     *
     * @param index index to look at
     * @return bus at given index
     */

    public Bus getAtIndex(int index) {
        return buses.get(index);
    }

    /**
     * Removes given bus from the list.
     *
     * @param bus bus to remove
     */

    public void removeBus(Bus bus) {
        buses.remove(bus);
    }

    /**
     * Finds all buses of given type.
     *
     * @param name type of bus to look by
     * @return arraylist of matching buses
     */

    public ArrayList<Bus> searchByType(String name) {
        ArrayList<Bus> busTemp = new ArrayList<>();

        for (Bus bus : buses) {
            if (bus.getClass().getName().equals(name)) busTemp.add(bus);
        }

        return busTemp;
    }

    /**
     * @return List converted to String
     */

    public String toString() {
        String result = "";
        for (Bus bus : buses) {
            result += bus.toString() + "\n";
        }
        return result;
    }

    /**
     * Finds all buses who are available in the given date interval.
     *
     * @param from start of date interval
     * @param to   end of date interval
     * @return BusList of all available in the given date interval
     */

    public BusList getAvailable(Date from, Date to) {
        ArrayList<Bus> inTrips = new ArrayList<>();
        BusList result = new BusList();
        TripList trips = DataHandler.getTrips();
        for (int i = 0; i < trips.getArrayTrip().size(); i++) {
            for (Bus buse : buses) {
                if (buse.equals(trips.getArrayTrip().get(i).getBus())) {
                    if (!inTrips.contains(buse))
                        inTrips.add(buse);
                    if (((from.before(trips.getArrayTrip().get(i).getDateObjStart())) && (to.before(trips.getArrayTrip().get(i).getDateObjStart())))
                            || ((from.after(trips.getArrayTrip().get(i).getDateObjEnd())) && (to.after(trips.getArrayTrip().get(i).getDateObjEnd())))) {
                       if(!result.getArrayBuses().contains(buse)) result.add(buse);
                    }
                }
            }
        }
        for (Bus buse : buses) {
            if (!inTrips.contains(buse))
                result.add(buse);
        }
        return result;
    }
}
