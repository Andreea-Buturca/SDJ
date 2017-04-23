package server.model;

import java.io.Serializable;

/**
 * Class which represents a mini bus and stores it's registration plate and number of seats.
 * Class extends Bus class.
 *
 * @author IT-1Y-A16 Group 1
 */

public class MiniBus extends Bus implements Serializable {

    /**
     * Constructs a mini bus
     *
     * @param registrationPlate Registration plate of bus
     * @param seatPlaces        number of seats in bus
     */

    public MiniBus(String registrationPlate, int seatPlaces) {
        super(registrationPlate, seatPlaces);
    }

    /**
     * @return returns bustype: Mini Bus
     */

    public String getBusType() {
        return "Mini Bus";
    }

    /**
     * @return bus converted to String
     */

    @Override
    public String toString() {
        return "Mini Bus, " + super.toString();
    }
}
