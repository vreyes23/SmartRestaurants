package com.smart.restaurant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Oscar on 10/23/16.
 */
public class Order {
    private int orderNumber;
    private String time;

    public Order(String customerOrder){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.time = dateFormat.format(date);
        Random rand = new Random();
        int  n = rand.nextInt(5000) + 1;
        this.orderNumber = n;
        createOrder(time, orderNumber, customerOrder);

    }
    public void createOrder(String time, int orderNumber, String customerOrder){
        // Call the Machine-Learning Class to obtain how accurate the convertedToText was
        // Menu Class    Menu menu = new Menu();
        // Waiting for the input
        //Object[] customerOrder = new Object[4];
        // customerOrder allocates 4 space of memory for the following:
        // Main menu items i.e Burger, Chicken Salad, Chicken Nuggets
        // Side items Milkshake, Ice Cream,
        // Fries, yes or no
        // Drink, yes or no

        System.out.println("Time " + time + "\n");
        System.out.println("orderNumber " + orderNumber + "\n");
        System.out.println("customerOrder " + customerOrder + "\n");

    }



}
