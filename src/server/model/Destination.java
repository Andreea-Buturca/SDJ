package server.model;

import java.io.Serializable;

/**
 * Class which represents a destination or stop.
 * Class stores place and if it is a stop then also time of stop.
 *
 * @author IT-1Y-A16 Group 1
 */

public class Destination implements Serializable {

    private String place;
    private String stopTime;

    /**
     * Constructs a destination.
     *
     * @param place place of destination
     */

    public Destination(String place) {
        this.place = place;
    }

    /**
     * Constructs a stop.
     *
     * @param place    place of a stop
     * @param stopTime time of a stop
     */

    public Destination(String place, String stopTime) {
        this.place = place;
        this.stopTime = stopTime;
    }

    /**
     * @return place of destination or stop
     */

    public String getPlace() {
        return place;
    }

    /**
     * @return destination or stop converted to String
     */

    @Override
    public String toString() {
        String time = "";
        if (stopTime != null) {
            time = ", " + stopTime + "min";
        }
        return place + time;
    }


}
