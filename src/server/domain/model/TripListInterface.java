package server.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by andreea on 4/28/2017.
 */
public interface TripListInterface {
    void addNewArrayList(ArrayList<Trip> tripArrayList);

    void add(Trip trip);

    int getSize();

    void remove(Trip trip);

    Trip get(int index);

    ArrayList<Trip> getArrayTrip();

    void sort();

    TripList findAllStandard();

    TripList findAllPrivate();

    TripList findAllByDate(LocalDate date);

    TripList findAllByDeparture(String departure);


    TripList findAllByDestination(String destination);


    TripList findAllByPassengers(String nrOfPassangers);


    String toString();
}
