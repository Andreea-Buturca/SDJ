package server.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class that represents a chauffeur.
 * Class extends Person class.
 *
 * @author IT-1Y-A16 Group 1
 */

public class Chauffeur extends Person implements Serializable {

    private int employeeID;
    private ArrayList<Integer> preferredDistance;
    private int workedHours;
    private boolean isVikar;
    private Bus bus;
    private ArrayList<String> busType;

    /**
     * Constructs a chauffeur object.
     *
     * @param name        name of chauffeur
     * @param address     address of chauffeur
     * @param email       email of chauffeur
     * @param phone       phone of chauffeur
     * @param dateOfBirth chauffeur's date of birth
     * @param employeeID  5-digit employee ID
     * @param isVikar     type of chauffeur's contract
     */

    public Chauffeur(String name, String address, String email, String phone, LocalDate dateOfBirth, int employeeID, boolean isVikar) {
        super(name, address, email, phone, dateOfBirth);
        if (this.employeeID >= 10000 & this.employeeID <= 99999) {
            this.employeeID = employeeID;
        }
        this.employeeID = employeeID;
        this.bus = null;
        this.isVikar = isVikar;
        this.preferredDistance = new ArrayList<>();
        this.busType = new ArrayList<>();
        this.workedHours = 0;
    }

    /**
     * @return name of chauffeur
     */

    public String getName() {
        return super.getName();
    }

    /**
     * @return address of chauffeur
     */

    public String getAddress() {
        return super.getAddress();
    }

    /**
     * @return chauffeur's email
     */

    public String getEmail() {
        return super.getEmail();
    }

    /**
     * @return chauffeur's phone
     */

    public String getPhone() {
        return super.getPhone();
    }

    /**
     * Sets chauffeur's preferred distance
     *
     * @param distance proffered distance
     */

    public void setPreferredShortDistance(int distance) {
        if (distance <= 400 && distance > 0)
            this.preferredDistance.add(distance);
    }

    /**
     * Sets chauffeur's preferred distance
     *
     * @param distance proffered distance
     */

    public void setPreferredMediumDistance(int distance) {
        if (distance > 400 && distance < 2500)
            this.preferredDistance.add(distance);
    }

    /**
     * Sets chauffeur's preferred distance
     *
     * @param distance proffered distance
     */

    public void setPreferredLongDistance(int distance) {
        if (distance >= 2500)
            this.preferredDistance.add(distance);
    }

    /**
     * @return Arraylist of chauffeur's preferred distances
     */

    public ArrayList<Integer> getPreferredDistance() {
        return preferredDistance;
    }

    /**
     * @return Arraylist of chauffeur's preferred bustypes
     */

    public ArrayList<String> getArrayBusTypes() {
        return this.busType;
    }

    /**
     * Sets preferred bustype
     *
     * @param bus preferred bustype
     */

    public void setPreferredBusType(String bus) {
        this.busType.add(bus);
    }

    /**
     * Compares this chauffeur to on given as argument.
     *
     * @param o object of type Chauffeur
     * @return true/false according to chauffeurs being equal
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chauffeur)) return false;
        if (!super.equals(o)) return false;

        Chauffeur chauffeur = (Chauffeur) o;

        if (employeeID != chauffeur.employeeID) return false;
        if (workedHours != chauffeur.workedHours) return false;
        if (isVikar != chauffeur.isVikar) return false;
        if (!preferredDistance.equals(chauffeur.preferredDistance)) return false;
        return bus != null ? bus.equals(chauffeur.bus) : chauffeur.bus == null && busType.equals(chauffeur.busType);
    }

    /**
     * Checks if chauffeur has vicar contract type.
     *
     * @return true if chauffeur has vicar contract type
     */

    public boolean isVikar() {
        return isVikar;
    }

    /**
     * Converts chauffeur to String.
     *
     * @return String og chauffeur
     */

    public String toString() {
        String preferredDistance = "";
        for (int i = 0; i < this.preferredDistance.size(); i++) {
            preferredDistance += ", Preferred distance: " + this.preferredDistance.get(i) + " km";
            if (i < this.preferredDistance.size() - 1)
                preferredDistance += ", ";
        }
        String preferredHours = "";
        if (this.workedHours < 17 && this.workedHours > 0) {
            preferredHours += ", Preferred working hours: " + this.workedHours + "h, in weekends ";
        }
        if (this.workedHours > 17) {
            preferredHours += ", Preferred working hours: " + this.workedHours + "h, during the week ";
        }
        String preferredBusType = "";
        for (int i = 0; i < this.busType.size(); i++) {
            preferredBusType += ", Preferred bus Type: " + this.busType.get(i) + "";
            if (i < this.busType.size() - 1)
                preferredBusType += ", ";
        }
        String vikar = "";
        if (this.isVikar) {
            vikar = ", Vikar employee";
        }
        return super.toString() + ", chauffeur ID: " + this.employeeID + preferredDistance + preferredHours + preferredBusType + vikar;
    }
}
