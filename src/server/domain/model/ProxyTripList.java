package server.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by andreea on 4/28/2017.
 */
public class ProxyTripList implements TripListInterface, Serializable {

    private TripList tripList;

    public ProxyTripList() {
        this.tripList = new TripList();
    }

    @Override
    public synchronized void addNewArrayList(ArrayList<Trip> tripArrayList) {
        this.tripList.addNewArrayList(tripArrayList);
    }

    @Override
    public synchronized void add(Trip trip) {
        this.tripList.add(trip);
    }

    @Override
    public synchronized int getSize() {
        return this.tripList.getSize();
    }

    @Override
    public synchronized void remove(Trip trip) {
        this.tripList.remove(trip);
    }

    @Override
    public synchronized Trip get(int index) {
        return this.tripList.get(index);
    }

    @Override
    public synchronized ArrayList<Trip> getArrayTrip() {
        return this.tripList.getArrayTrip();
    }

    @Override
    public synchronized void sort() {
        this.tripList.sort();
    }

    @Override
    public synchronized TripList findAllByDate(LocalDate date) {
        return this.tripList.findAllByDate(date);
    }

}
