package com.restuarants.smart.speechtotextwatsonv7;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

/**
 * This Class, CustomerDB connects to the NOSQL IBM Bluemix Database. Allows connections and sends
 * and retrieves data.
 */
public class CustomerDB {
    private String _ticketid;
    private String firstName;
    private String lastName;
    private LinkedList<String> order;
    private Date creationDate;
    private int age;
    public CustomerDB()
    {
        new CustomerDB("firstname", "lastName", new Date(), -1);
    }
    public CustomerDB(String firstName, String lastName, Date creationDate, int age) {
        this._ticketid = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.age = age;
    }
    public String get_ticketid() {
        return _ticketid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

