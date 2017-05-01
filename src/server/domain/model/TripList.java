package server.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Class which represents a list of trips.
 *
 * @author IT-1Y-A16 Group 1
 */

public class TripList implements Serializable, TripListInterface {

    private ArrayList<Trip> trips;

    /**
     * Constructs a list of trips.
     */

    public TripList() {
        this.trips = new ArrayList<>();
    }

    /**
     * Replace arraylist with new.
     *
     * @param tripArrayList<Trip> tripArrayList to addNewArrayList
     */
@Override
    public void addNewArrayList(ArrayList<Trip> tripArrayList) {
        this.trips = tripArrayList;
    }

    /**
     * Adds a given trip to the list.
     *
     * @param trip trip to add
     */
    @Override
    public void add(Trip trip) {
        this.trips.add(trip);
    }

    /**
     * @return size of trip list
     */
    @Override
    public int getSize() {
        return trips.size();
    }

    /**
     * Removes given trip from the list.
     *
     * @param trip trip to remove
     */
    @Override
    public void remove(Trip trip) {
        trips.remove(trip);
    }

    /**
     * Finds trip at the given index.
     *
     * @param index index to look at
     * @return trip at given index
     */
    @Override
    public Trip get(int index) {
        return trips.get(index);
    }

    /**
     * @return arraylist of all trips in the list
     */
    @Override
    public ArrayList<Trip> getArrayTrip() {
        return trips;
    }

    /**
     * Sort trips by date
     */
    @Override
    public void sort() {
        getArrayTrip().sort(Comparator.comparing(Trip::getDateObjStart));
        addNewArrayList(getArrayTrip());
    }

    /**
     * Finds all trips that starts at given date.
     *
     * @param date date to find by
     * @return TripList of all trips that starts at given date
     */
    @Override
    public TripList findAllByDate(LocalDate date) {
        TripList result = new TripList();
        for (Trip trip : trips) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(trip.getDateObjStart());
            if ((cal.get(Calendar.YEAR) == date.getYear())
                    && (cal.get(Calendar.MONTH) == date.getMonthValue())
                    && (cal.get(Calendar.DAY_OF_MONTH) == date.getDayOfMonth())) {
                result.add(trip);
            }
        }
        return result;
    }

    /**
     * @return list of trips converted to String
     */
    @Override
    public String toString() {
        String result = "";
        for (Trip trip : trips) result += trip.toString() + "\n";
        return result;
    }
}
