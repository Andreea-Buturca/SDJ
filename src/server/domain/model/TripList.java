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

public class TripList implements Serializable {

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

    public void addNewArrayList(ArrayList<Trip> tripArrayList) {
        this.trips = tripArrayList;
    }

    /**
     * Adds a given trip to the list.
     *
     * @param trip trip to add
     */

    public void add(Trip trip) {
        this.trips.add(trip);
    }

    /**
     * @return size of trip list
     */

    public int getSize() {
        return trips.size();
    }

    /**
     * Removes given trip from the list.
     *
     * @param trip trip to remove
     */

    public void remove(Trip trip) {
        trips.remove(trip);
    }

    /**
     * Finds trip at the given index.
     *
     * @param index index to look at
     * @return trip at given index
     */

    public Trip get(int index) {
        return trips.get(index);
    }

    /**
     * @return arraylist of all trips in the list
     */

    public ArrayList<Trip> getArrayTrip() {
        return trips;
    }

    /**
     * Sort trips by date
     */
    public void sort() {
        getArrayTrip().sort(Comparator.comparing(Trip::getDateObjStart));
        addNewArrayList(getArrayTrip());
    }

    /**
     * Finds all trips that are not private.
     *
     * @return TripList of all trips that are not private.
     */

    public TripList findAllStandard() {
        TripList result = new TripList();
        for (Trip trip : trips) {
            if (!trip.isPrivate()) {
                result.add(trip);
            }
        }
        return result;
    }

    /**
     * Finds all trips that are private.
     *
     * @return TripList of all trips that are private.
     */

    public TripList findAllPrivate() {
        TripList result = new TripList();
        for (Trip trip : trips) {
            if (trip.isPrivate()) {
                result.add(trip);
            }
        }
        return result;
    }

    /**
     * Finds all trips that starts at given date.
     *
     * @param date date to find by
     * @return TripList of all trips that starts at given date
     */

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
     * Finds all trips that starts that start from given place.
     *
     * @param departure place of departure
     * @return TripList of all trips that starts at given place
     */

    public TripList findAllByDeparture(String departure) {
        TripList result = new TripList();
        for (Trip trip : trips) {
            if (trip.getPickUpPoint().getPlace().equals(departure)) {
                result.add(trip);
            }
        }
        return result;
    }

    /**
     * Finds all trips with given destination.
     *
     * @param destination destination to find by
     * @return TripList of all with given destination
     */

    public TripList findAllByDestination(String destination) {
        TripList result = new TripList();
        for (Trip trip : trips) {
            if (trip.getDestination().getPlace().equals(destination)) {
                result.add(trip);
            }
        }
        return result;
    }

    /**
     * Finds all trips which have more free spaces than given number of passengers.
     *
     * @param nrOfPassangers number of passengers
     * @return TripList of all trips with more space as given number
     */

    public TripList findAllByPassengers(String nrOfPassangers) {
        int passengers = Integer.parseInt(nrOfPassangers);
        TripList result = new TripList();
        for (Trip trip : trips) {
            if (trip.getFreeSpaces() > passengers) {
                result.add(trip);
            }
        }
        return result;
    }

    /**
     * @return list of trips converted to String
     */

    public String toString() {
        String result = "";
        for (Trip trip : trips) result += trip.toString() + "\n";
        return result;
    }
}
