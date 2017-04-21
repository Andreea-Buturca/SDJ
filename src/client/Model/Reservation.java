package client.Model;

import java.io.Serializable;

/**
 * Class that represents reservation.
 *
 * @author IT-1Y-A16 Group 1
 */

public class Reservation implements Serializable {

    private Trip trip;
    private Customer customer;
    private PassengerList passengerList;
    private double defaultPricePerson;
    private double finalPrice;
    private double priceExtraServices;
    private double priceExtraServicesYoung;
    private double discount;

    /**
     * Constructs object of reservation with trip, customer, list of passengers and price.
     *
     * @param trip          trip
     * @param customer      customer
     * @param passengerList list pf passengers
     * @param price         price
     */

    public Reservation(Trip trip, Customer customer, PassengerList passengerList, double price) {
        this.trip = trip;
        this.customer = customer;
        this.passengerList = passengerList;
        this.trip.freeSpaces -= this.passengerList.getArrayPassenger().size();
        this.defaultPricePerson = price;
        this.priceExtraServices = 0;
        this.priceExtraServicesYoung = 0;
        this.discount = 0;
        this.finalPrice = 0;
    }

    /**
     * @return customer from reservation
     */

    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * @return destination from reservation's trip
     */

    public Destination getReservationDestination() {
        return this.trip.getDestination();
    }

    /**
     * @return list of all passengers
     */

    public PassengerList getReservationPassengers() {
        return this.passengerList;
    }

    /**
     * @return default price for one person
     */

    public double getDefaultPricePerson() {
        return defaultPricePerson;
    }

    /**
     * @return price for adult extra services
     */

    public double getPriceExtraServices() {
        return priceExtraServices;
    }

    /**
     * Sets price for extra services.
     *
     * @param priceExtraServices price to set
     */

    public void setPriceExtraServices(double priceExtraServices) {
        this.priceExtraServices = priceExtraServices;
    }


    /**
     * @return price for under 18 extra services
     */
    public double getPriceExtraServicesYoung() {
        return priceExtraServicesYoung;
    }

    /**
     * Sets price for extra services under 18.
     *
     * @param priceExtraServicesYoung price to set
     */
    public void setPriceExtraServicesYoung(double priceExtraServicesYoung) {
        this.priceExtraServicesYoung = priceExtraServicesYoung;
    }

    /**
     * @return trip from reservation
     */

    public Trip getTrip() {
        return trip;
    }

    /**
     * @return discount
     */

    public double getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount discount
     */

    public void setDiscount(double discount) {
        this.discount = (discount);
    }

    /**
     * Sets final price for the reservation according to price for person and number of passengers, discount and price for extra services.
     */

    public void setFinalPrice() {
        this.finalPrice = ((getDefaultPricePerson() + getPriceExtraServices()) - getDiscount()) * this.passengerList.getArrayPassenger().size();
    }

    /**
     * @return Reservation converted to String
     */

    public String toString() {
        String passsengers = "";
        for (int i = 0; i < passengerList.getArrayPassenger().size(); i++) {
            passsengers += " , Passenger nr. " + (i + 1) + ": " + this.passengerList.getArrayPassenger().get(i).toString();
        }
        return "Reservation for Customer: " + this.customer + passsengers + " , Total price: " + this.finalPrice + " , " + this.trip;
    }
}
