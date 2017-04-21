package client.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

/**
 * Class that represents trip, tour or bus and chauffeur when private.
 *
 * @author IT-1Y-A16 Group 1
 */

public class Trip implements Serializable {

    public Integer freeSpaces;
    private Bus bus;
    private Chauffeur chauffeur;
    private Destination pickUpPoint;
    private Destination destination;
    private DestinationList stops;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String timeStart;
    private String timeEnd;
    private Customer customer;
    private boolean isPrivate;
    private String privateString;
    private int distance;
    private Integer price;
    private String duration;
    private Date dateObjStart;
    private Date dateObjEnd;
    private boolean food = false;
    private boolean accommodation = false;
    private boolean tickets = false;

    /**
     * Constructs a trip object;
     *
     * @param bus         bus in the trip
     * @param chauffeur   chauffeur for bus
     * @param pickUpPoint destination from which trip starts
     * @param destination trip destination
     * @param distance    overall distance of destination
     * @param dateStart   start date of the trip
     * @param timeStart   start time of the trip
     * @param dateEnd     end date of the trip
     * @param timeEnd     end time of the trip
     * @param price       normal price for the trip
     */

    public Trip(Bus bus, Chauffeur chauffeur, Destination pickUpPoint, Destination destination, int distance, LocalDate dateStart, String timeStart, LocalDate dateEnd, String timeEnd, int price) {
        this.bus = bus;
        this.freeSpaces = this.bus.getSeatPlaces();
        this.chauffeur = chauffeur;
        this.pickUpPoint = pickUpPoint;
        this.destination = destination;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.distance = distance;
        this.price = price;

        String[] lineToken = timeStart.split(":");
        int hours = Integer.parseInt(lineToken[0]);
        int minutes = Integer.parseInt(lineToken[1]);
        this.dateObjStart = new Date(dateStart.getYear() - 1900, dateStart.getMonthValue(), dateStart.getDayOfMonth(), hours, minutes);

        lineToken = timeEnd.split(":");
        hours = Integer.parseInt(lineToken[0]);
        minutes = Integer.parseInt(lineToken[1]);

        this.dateObjEnd = new Date(dateEnd.getYear() - 1900, dateEnd.getMonthValue(), dateEnd.getDayOfMonth(), hours, minutes);
        this.duration = this.getDuration(this.dateObjStart, this.dateObjEnd);
        privateString = "False";

    }

    /**
     * Creates String version of duration between two given dates.
     *
     * @param start start of date interval
     * @param end   end of date interval
     * @return String version of duration
     */

    private String getDuration(Date start, Date end) {
        String result = "";
        long diff = end.getTime() - start.getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        if (diffDays > 0) {
            result += diffDays + " day ";
        }
        diff -= diffDays * (24 * 60 * 60 * 1000);

        int diffhours = (int) (diff / (60 * 60 * 1000));
        if (diffhours > 0) {
            result += diffhours + " hour ";
        }
        diff -= diffhours * (60 * 60 * 1000);

        int diffmin = (int) (diff / (60 * 1000));
        if (diffmin > 0) {
            result += diffmin + " min ";
        }
        return result;
    }

    /**
     * Checks if food is provided in this trip.
     */

    public boolean isFood() {
        return food;
    }

    /**
     * Sets if trip provides food for passengers.
     */

    public void setFood(boolean food) {
        this.food = food;
    }

    /**
     * Checks if accommodation is provided in this trip.
     */

    public boolean isAccommodation() {
        return accommodation;
    }

    /**
     * Sets if trip provides accommodation for passengers.
     */

    public void setAccommodation(boolean accommodation) {
        this.accommodation = accommodation;
    }

    /**
     * Checks if tickets are provided in this trip.
     */

    public boolean isTickets() {
        return tickets;
    }

    /**
     * Sets if trip provides tickets for passengers.
     *
     * @param tickets true if ticket are provided
     */

    public void setTickets(boolean tickets) {
        this.tickets = tickets;
    }

    /**
     * @return bus from the trip
     */

    public Bus getBus() {
        return bus;
    }

    /**
     * @return time of trip start;
     */

    public String getTimeStart() {
        return timeStart;
    }

    /**
     * @return time of trip end
     */

    public String getTimeEnd() {
        return timeEnd;
    }

    /**
     * @return number of free spaces for the trip
     */

    public Integer getFreeSpaces() {
        return freeSpaces;
    }

    /**
     * @return destination of trip start
     */

    public Destination getPickUpPoint() {
        return pickUpPoint;
    }

    /**
     * @return destination for the trip
     */

    public Destination getDestination() {
        return destination;
    }

    /**
     * @return chauffeur for the trip
     */

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    /**
     * @return DestinationList of all the stops
     */

    public DestinationList getStops() {
        return stops;
    }

    /**
     * Sets stops for the trip.
     *
     * @param stops DestinationList of the stops for the trip
     */

    public void setStops(DestinationList stops) {
        this.stops = stops;
    }

    /**
     * @return date of trip start
     */

    public LocalDate getDateStart() {
        return dateStart;
    }

    /**
     * @return date of trip end
     */

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    /**
     * @return trip's customer
     */

    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer for the trip.
     * When customer is set, trip becomes private... Bus and Chauffeur.
     *
     * @param customer customer to set trip to
     */

    public void setCustomer(Customer customer) {
        this.isPrivate = true;
        privateString = "True";
        this.customer = customer;
        this.freeSpaces = 0;
    }

    /**
     * Checks if trip is private.
     */

    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * @return distance of the trip
     */

    public int getDistance() {
        return distance;
    }

    /**
     * @return price of the trip
     */

    public int getPrice() {
        return price;
    }

    /**
     * @return Date object of trip start
     */

    public Date getDateObjStart() {
        return dateObjStart;
    }

    /**
     * @return Date object of trip end
     */

    public Date getDateObjEnd() {
        return dateObjEnd;
    }

    /**
     * @return trip converted to string
     */

    public String toString() {
        String extra = "";
        if (isTickets() || isAccommodation() || isFood()) {
            extra = ", Extra services ";
        }
        if (isFood()) {
            extra += "- food";
        }
        if (isAccommodation()) {
            extra += "- accomodation";
        }
        if (isTickets()) {
            extra += "- tickets";
        }
        String tour = "";
        if (this.isPrivate()) {
            tour += "Private Tour for customer: "+ this.customer+ ", ";
        } else if (!this.isPrivate())
        {
            if(!this.dateStart.equals(this.dateEnd))
            tour += "Travel to: ";
            else if(this.dateStart.equals(this.dateEnd))
                tour += "Trip to: ";
        }
        String stop = "";
        if(!this.stops.isEmpty())
        {
            stop += ", Stop: " +this.stops.toString()+" ";
        }

        return tour + this.destination + ", place of departure: " + this.pickUpPoint.toString() + ", departure time and date: " + this.timeStart + ", " + this.dateStart + stop + ", arrival time and date: " + this.timeEnd + ", " + this.dateEnd + ", distance: " + this.distance + " km" + extra + ", standard price/pers: " + this.price + " dkk, " + ", free seats: "+ this.freeSpaces+" ," +this.bus.toString() + ", chauffeur: " + this.chauffeur.toString();
    }

    static class TripDateComparator implements Comparator<Trip> {
        public int compare(Trip trip1, Trip trip2) {
            Date today = new Date();
            return (int) ((today.getTime() - trip1.getDateObjStart().getTime()) - (today.getTime() - trip2.getDateObjStart().getTime()));
        }
    }
}
