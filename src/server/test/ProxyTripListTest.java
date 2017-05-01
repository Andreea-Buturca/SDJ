package server.test;

import server.domain.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aykon on 01-May-17.
 */
public class ProxyTripListTest {

    private ProxyTripList proxyTripList;
    private Bus bus1;
    private Bus bus2;
    private Chauffeur chauffeur1;
    private Chauffeur chauffeur2;
    private Passenger passenger1;
    private Passenger passenger2;
    private Customer customer1;
    private Customer customer2;
    private Destination destination1;
    private Destination destination2;
    private Trip trip1;
    private Trip trip2;
    private Trip trip3;
    private Trip trip4;
    private Trip trip5;
    private TripList tripList1;
    private ArrayList<Trip> tripArrayList;


    @org.junit.Before
    public void setUp() throws Exception {
        proxyTripList = new ProxyTripList();
        bus1 = new ClassicBus("AA12345", 25);
        bus2 = new ClassicBus("AA56789", 50);
        chauffeur1 = new Chauffeur("Name2", "address", "aa@bb.com", "123456", LocalDate.of(1997, 01, 10), 12345, false);
        chauffeur2 = new Chauffeur("Name2", "address", "aab@bb.com", "123456", LocalDate.of(1997, 01, 10), 12345, false);
        passenger1 = new Passenger("NamePassenger1", "address", "aa@bb.com", LocalDate.of(1997, 01, 10));
        passenger2 = new Passenger("NamePassenger2", "address", "aab@bb.com", LocalDate.of(1997, 01, 10));
        customer1 = new Customer("NameCustomer1", "address", "aa@bb.com", "123456");
        customer2 = new Customer("NameCustomer2", "address", "aab@bb.com", "123456");
        destination1 = new Destination("DestinationName1");
        destination2 = new Destination("DestinationName2");
        trip1 = new Trip(bus2, chauffeur2, destination1, destination2, 12, LocalDate.of(2018, 8, 6), "12:00", LocalDate.of(2018, 8, 10), "12:00", 100);
        trip2 = new Trip(bus1, chauffeur1, destination1, destination2, 12, LocalDate.of(2018, 7, 6), "12:00", LocalDate.of(2018, 7, 10), "12:00", 100);
        trip3 = new Trip(bus1, chauffeur1, destination1, destination2, 12, LocalDate.of(2018, 6, 6), "12:00", LocalDate.of(2018, 6, 10), "12:00", 100);
        trip4 = new Trip(bus2, chauffeur2, destination2, destination1, 12, LocalDate.of(2018, 9, 6), "12:00", LocalDate.of(2018, 9, 10), "12:00", 100);
        trip5 = new Trip(bus2, chauffeur2, destination2, destination1, 12, LocalDate.of(2018, 10, 6), "12:00", LocalDate.of(2018, 10, 10), "12:00", 100);
        tripList1 = new TripList();
        tripList1.add(trip1);
        tripList1.add(trip2);
        proxyTripList.add(trip1);
        proxyTripList.add(trip2);
        tripArrayList = new ArrayList<>();
        tripArrayList.add(trip3);
        tripArrayList.add(trip4);
        tripArrayList.add(trip5);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void addNewArrayList() throws Exception {
        assertEquals("Size of trips", 2, proxyTripList.getSize());
        proxyTripList.addNewArrayList(tripArrayList);
        assertEquals("Size of trips", 3, proxyTripList.getSize());
        assertSame("Same arrays", tripArrayList, proxyTripList.getArrayTrip());
    }

    @org.junit.Test
    public void add() throws Exception {
        assertEquals("Size of trips", 2, proxyTripList.getSize());
        proxyTripList.add(trip4);
        assertEquals("Size of trips", 3, proxyTripList.getSize());
        proxyTripList.add(trip3);
        proxyTripList.add(trip5);
        assertEquals("Size of trips", 5, proxyTripList.getSize());
        assertSame("Same Trip", trip5, proxyTripList.get((proxyTripList.getSize() - 1)));

    }

    @org.junit.Test
    public void getSize() throws Exception {
        assertEquals("Size of trips", 2, proxyTripList.getSize());
        proxyTripList.add(trip4);
        assertEquals("Size of trips", 3, proxyTripList.getSize());
        proxyTripList.add(trip3);
        proxyTripList.add(trip5);
        assertEquals("Size of trips", 5, proxyTripList.getSize());
        assertEquals("Size of trips", proxyTripList.getArrayTrip().size(), proxyTripList.getSize());
    }

    @org.junit.Test
    public void get() throws Exception {
        assertSame("Same Trip", trip1, proxyTripList.get(0));
        assertSame("Same Trip", trip2, proxyTripList.get(1));
        proxyTripList.add(trip5);
        assertSame("Same Trip", trip5, proxyTripList.get((proxyTripList.getSize() - 1)));
        proxyTripList.addNewArrayList(tripArrayList);
        assertSame("Same Trip", tripArrayList.get(0), proxyTripList.get(0));
        assertSame("Same Trip", tripArrayList.get(2), proxyTripList.get(2));
    }

    @org.junit.Test
    public void getArrayTrip() throws Exception {
        proxyTripList.addNewArrayList(tripArrayList);
        assertSame("Same arrayList", tripArrayList, proxyTripList.getArrayTrip());
    }

    @org.junit.Test
    public void sort() throws Exception {
        proxyTripList.add(trip3);
        proxyTripList.sort();
        assertSame(trip3, proxyTripList.get(0));
        assertSame(trip2, proxyTripList.get(1));
        assertSame(trip1, proxyTripList.get(2));

    }

    @org.junit.Test
    public void findAllByDate() throws Exception {
        assertSame(trip2, proxyTripList.findAllByDate(LocalDate.of(2018, 7, 6)).get(0));
    }


}