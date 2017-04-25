package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class which represents a list of customers.
 *
 * @author IT-1Y-A16 Group 1
 */

public class CustomerList implements Iterable<Customer>, Serializable {

    private ArrayList<Customer> customers = new ArrayList<>();

    /**
     * Constructs a list of customers.
     */

    public CustomerList() {
    }

    /**
     * Adds a given customer to the list.
     *
     * @param customer customer to add
     */

    public void add(Customer customer) {
        customers.add(customer);
    }

    /**
     * Removes given customer from the list.
     *
     * @param customer customer to remove
     */

    public void remove(Customer customer) {
        customers.remove(customer);
    }

    /**
     * @return size of customer list
     */

    public int getSize() {
        return customers.size();
    }

    /**
     * Finds customer at given index;
     *
     * @param index index to find customer at
     * @return customer at given index
     */

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    /**
     * Finds all customers with given name.
     *
     * @param name name to look by
     * @return CustomerList of all customers with given name
     */

    public CustomerList findAllByName(String name) {
        CustomerList result = new CustomerList();
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                result.add(customer);
        }
        return result;
    }

    /**
     * Finds all customers with given address.
     *
     * @param address address to look by
     * @return CustomerList of all customers with given address
     */

    public CustomerList findAllByAddress(String address) {
        CustomerList result = new CustomerList();
        for (Customer customer : customers) {
            if (customer.getAddress().equals(address))
                result.add(customer);
        }
        return result;
    }

    /**
     * Finds all customers with given phone;
     *
     * @param phone phone to look by
     * @return CustomerList of all customers with given phone
     */

    public CustomerList findAllByPhone(String phone) {
        CustomerList result = new CustomerList();
        for (Customer customer : customers) {
            if (customer.getPhone().equals(phone))
                result.add(customer);
        }
        return result;
    }

    /**
     * Finds all customers with given email;
     *
     * @param email email to look by
     * @return CustomerList of all customers with given email
     */

    public CustomerList findAllByEmail(String email) {
        CustomerList result = new CustomerList();
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email))
                result.add(customer);
        }
        return result;
    }

    /**
     * Finds all companies with given company name.
     *
     * @param companyName company name to look by
     * @return CustomerList of all companies with given company name
     */

    public CustomerList findAllByCompanyName(String companyName) {
        CustomerList result = new CustomerList();
        for (Customer customer : customers) {
            if (customer.getCompanyName().equals(companyName))
                result.add(customer);
        }
        return result;
    }

    /**
     * Gets index of given customer.
     *
     * @param customer customer to find index
     * @return index of given customer
     */

    public int getIndex(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).equals(customer))
                return i;
        }
        return -1;
    }

    /**
     * @return arraylist of all customers in the list
     */

    public ArrayList<Customer> getArrayCustomer() {
        return customers;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < customers.size(); i++) {
            s += customers.get(i);
            if (i < customers.size() - 1)
                s += "\n";
        }
        return s;
    }

    @Override
    public Iterator<Customer> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<Customer>
    {
        private int currentIndex;

        public ArrayListIterator()
        {
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext()
        {
            return currentIndex < getSize();
        }

        @Override
        public Customer next()
        {
            if (!hasNext())
                throw new IllegalStateException("Element not found");
            Customer customer = customers.get(currentIndex);
            currentIndex++;
            return customer;
        }
    }
}