package com.example.oscar.smartrestaurants;

/**
 * Created by Oscar on 10/8/16.
 */
public class Customer {
    private String name;
    private String ticketID;
    Order order = new Order();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }
}
