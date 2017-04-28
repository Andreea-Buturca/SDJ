package server.domain.model;

import java.io.Serializable;

/**
 * Class which represents a classic bus and stores it's registration plate and number of seats.
 * Class extends Bus class.
 *
 * @author IT-1Y-A16 Group 1
 */
public class ClassicBus extends Bus implements Serializable {

    /**
     * Constructs a classic bus
     *
     * @param registrationPlate egistration plate of bus
     * @param seatPlaces        number of seats in bus
     */

    public ClassicBus(String registrationPlate, int seatPlaces) {
        super(registrationPlate, seatPlaces);
    }

    /**
     * @return returns bustype: Classic Bus
     */

    public String getBusType() {
        return "Clasic Bus";
    }

    /**
     * @return bus converted to String
     */

    public String toString() {
        return "Classic Bus, " + super.toString();
    }
}
