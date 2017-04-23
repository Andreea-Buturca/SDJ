package server.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class which represents a list of destinations or stops.
 *
 * @author IT-1Y-A16 Group 1
 */

public class DestinationList implements Serializable {

    private ArrayList<Destination> destinations = new ArrayList<>();

    /**
     * Constructs a list of destinations.
     */

    public DestinationList() {
    }

    /**
     * Adds a given destination to the list.
     *
     * @param destination destination to add
     */

    public void add(Destination destination) {
        destinations.add(destination);
    }

    /**
     * @return arraylist of all destinations in list
     */

    public ArrayList<Destination> getArrayDestination() {
        return destinations;
    }

    /**
     * @param index index of destination
     * @return destination at given index
     */

    public Destination getAtIndex(int index) {
        return destinations.get(index);
    }

    /**
     * Removes given destination from the list.
     *
     * @param destination destination to remove
     */

    public void removeDestination(Destination destination) {
        destinations.remove(destination);
    }

    /**
     * Finds a destination in the list according to place given as argument.
     *
     * @param name place to look by
     * @return destination with place equal to one give as argument
     */

    public Destination findByName(String name) {
        for (Destination destination : destinations) {
            if (destination.getPlace().equals(name)) return destination;
        }
        return null;
    }

    public boolean isEmpty(){
        return destinations.isEmpty();
    }

    /**
     * @return DestinationList converted to String
     */
    public String toString() {
        String x = "";
        for (int i = 0; i < this.destinations.size(); i++) {
            x += this.destinations.get(i) + " ";
            if (this.destinations.size() - 1 > i) {
                x += ",";
            }
        }
        return x;
    }
}
