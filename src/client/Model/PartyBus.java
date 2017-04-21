package client.Model;

import java.io.Serializable;

/**
 * Class which represents a party bus and stores it's registration plate and number of seats.
 * Class extends Bus class.
 *
 * @author IT-1Y-A16 Group 1
 */

public class PartyBus extends Bus implements Serializable {

    /**
     * Constructs a party bus
     *
     * @param registrationPlate Registration plate of bus
     * @param seatPlaces        number of seats in bus
     */

    public PartyBus(String registrationPlate, int seatPlaces) {
        super(registrationPlate, seatPlaces);
    }

    /**
     * @return returns bustype: Party Bus
     */

    public String getBusType() {
        return "Party Bus";
    }

    /**
     * @return bus converted to String
     */

    @Override
    public String toString() {
        return "Party Bus, " + super.toString();
    }
}
