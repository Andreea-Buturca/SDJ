package server.domain.model;

import java.io.Serializable;

/**
 * Class which represents a luxury bus and stores it's registration plate and number of seats.
 * Class extends Bus class.
 *
 * @author IT-1Y-A16 Group 1
 */

public class LuxuryBus extends Bus implements Serializable {

    /**
     * Constructs a luxury bus
     *
     * @param registrationPlate Registration plate of bus
     * @param seatPlaces        number of seats in bus
     */

    public LuxuryBus(String registrationPlate, int seatPlaces) {
        super(registrationPlate, seatPlaces);
    }

    /**
     * @return returns bustype: Luxury Bus
     */

    public String getBusType() {
        return "Luxury Bus";
    }

    /**
     * @return bus converted to String
     */

    public String toString() {
        return "Luxury Bus, " + super.toString();
    }
}
