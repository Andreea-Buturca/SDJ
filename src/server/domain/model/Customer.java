package server.domain.model;

import java.io.Serializable;

/**
 * Class that represents customer and stores customer's points for trip.
 * If customer is company, company's name is stored.
 * Class extends Person.
 *
 * @author IT-1Y-A16 Group 1
 */

public class Customer extends Person implements Serializable {

    private boolean isCompany;
    private String companyName;
    private int points;

    /**
     * Constructs a Customer as a company
     *
     * @param name        name of customer
     * @param address     address of customer
     * @param email       email of customer
     * @param phone       phone of customer
     * @param isCompany   true if it is a company
     * @param companyName company's name
     */

    public Customer(String name, String address, String email, String phone, boolean isCompany, String companyName) {
        super(name, address, email, phone);
        this.isCompany = isCompany;
        this.companyName = companyName;
        this.points = 0;
    }

    /**
     * Constructs a Customer object
     *
     * @param name    name of customer
     * @param address address of customer
     * @param email   email of customer
     * @param phone   phone of customer
     */

    public Customer(String name, String address, String email, String phone) {
        super(name, address, email, phone);
    }

    /**
     * method that compares given customer to this one.
     *
     * @param o object oc typer customer
     * @return true/false according to customers being the same
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        if (isCompany != customer.isCompany) return false;
        if (points != customer.points) return false;
        return companyName.equals(customer.companyName);

    }

    /**
     * Method that gives a customer point.
     */

    public void addPointToCustomer() {
        this.points++;
    }

    /**
     * @return company's name of type String
     */

    public String getCompanyName() {
        return companyName;
    }

    /**
     * Method that checks if customer is frequent.
     *
     * @return true if customer has more then 5 points
     */

    private boolean isFrequent() {
        return points > 5;
    }

    /**
     * Converts customer to string.
     *
     * @return String of customer
     */

    public String toString() {
        String x = "";
        if (isCompany) {
            x += ", Company name: " + this.companyName;
        }
        String frequent = "";
        if (isFrequent()) {
            frequent += ", Frequent customer, nr of points: " + this.points;
        } else if (!isFrequent()) {
            frequent += ", nr of points: " + this.points;
        }
        return super.toString() + x + frequent;
    }

}