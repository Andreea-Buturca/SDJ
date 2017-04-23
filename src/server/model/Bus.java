package server.model;

import java.io.Serializable;

/**
 * Abstract class which represents a bus and stores it's registration plate and number of seats.
 *
 * @author IT-1Y-A16 Group 1
 */

public abstract class Bus implements Serializable {

    private final String registrationPlate;
    private final int seatPlaces;

    /**
     * Constructs a Bus object.
     *
     * @param registrationPlate registration plate of bus
     * @param seatPlaces        number of seats in the bus
     */

    Bus(String registrationPlate, int seatPlaces) {
        this.registrationPlate = registrationPlate;
        this.seatPlaces = seatPlaces;
    }

    /**
     * @return number of seats
     */

    public int getSeatPlaces() {
        return seatPlaces;
    }

    /**
     * @return bus converted to String
     */

    public String toString() {
        return registrationPlate + ", " + seatPlaces + " seats";

    }

    /**
     * Compares given bus to this one.
     *
     * @param o bus to compare to
     * @return true/false when equal
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;

        Bus bus = (Bus) o;

        return seatPlaces == bus.seatPlaces && registrationPlate.equals(bus.registrationPlate);
    }

    public abstract String getBusType();
}
