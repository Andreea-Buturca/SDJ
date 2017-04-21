package client.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;


/**
 * Abstract class that stores name, address, email, phone and date of birth of person.
 *
 * @author IT-1Y-A16 Group 1
 */

class Person implements Serializable {

    private String name;
    private String address;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;

    /**
     * Method which constructs a object of person without a date of birth.
     *
     * @param name    name of person
     * @param address address of person
     * @param email   email of person
     * @param phone   phone of person
     */

    Person(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Method which constructs a object of person.
     *
     * @param name        name of person
     * @param address     address of person
     * @param email       email of person
     * @param phone       phone of person
     * @param dateOfBirth person's date of birth
     */

    Person(String name, String address, String email, String phone, LocalDate dateOfBirth) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Method which constructs a object of person without a phone
     *
     * @param name        name of person
     * @param address     address of person
     * @param email       email of person
     * @param dateOfBirth person's date of birth
     */

    Person(String name, String address, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return person's name of type String
     */

    public String getName() {
        return name;
    }

    /**
     * @return person's address of type String
     */

    public String getAddress() {
        return address;
    }

    /**
     * @return person's email of type String
     */

    public String getEmail() {
        return email;
    }

    /**
     * @return person's date of birth
     */

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return person's phone of type String
     */

    public String getPhone() {
        return phone;
    }

    /**
     * method that compares given person in argument to this one.
     *
     * @param o object of type Person
     * @return true if persons are equal / false if not
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!name.equals(person.name)) return false;
        if (!address.equals(person.address)) return false;
        if (!email.equals(person.email)) return false;
        return phone.equals(person.phone) && dateOfBirth.equals(person.dateOfBirth);
    }


    /**
     * Method that checks if person is more than 18 years old.
     *
     * @return true if adult
     */

    public boolean isAdult() {
        if (dateOfBirth != null) {
            LocalDate bday = getDateOfBirth();
            LocalDate today = LocalDate.now();
            if (Period.between(bday, today).getYears() > 18)
                return true;
            else
                return false;
        }
        return true;
    }

    /**
     * method that converts the person to String
     *
     * @return String version of person
     */

    public String toString() {
        String email = "";
        if (!this.email.equals("")) {
            email += ", email: " + this.email;
        }
        String phone = "";
        if (this.phone != null) {
            phone += ", phone: " + this.phone;
        }
        String address = "";
        if (!this.address.equals("")) {
            address += ", address: " + this.address;
        }
        String minor = "";
        if (!isAdult()) {
            minor += ", Minor person.";
        }
        if (this.dateOfBirth != null) {
            return this.name + address + email + phone + ", birthday: " + this.dateOfBirth + minor;
        } else {
            return this.name + address + email + phone;
        }
    }
}
