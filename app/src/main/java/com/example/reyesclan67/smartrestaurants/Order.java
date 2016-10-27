package com.example.reyesclan67.smartrestaurants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Victor Reyes on 10/26/2016.
 * Smart Restaurants Application
 * For CS465 Applied Cognitive Computing
 */

public class Order {
    private String id;
    private String time;
    private double totalPrice;
    private String customerName;
    private ArrayList<Food> foodItems;


    public Order(String customerName) {
        this.customerName = customerName;
        this.totalPrice = 0.00;
        this.id = UUID.randomUUID().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.time = dateFormat.format(new Date());
        //this.foodItems.add(new Food("Hamburger"));
    }

    public void setCustomerName(String name){
        this.customerName= name;
    }

    public void addFood(String foodName){
        //this.foodItems.add(new Food(foodName));
        //setTotalPrice();
    }

    public void setTotalPrice(){
        this.totalPrice = 0.00;
        for (int i = 0; i<foodItems.size();i++){
            double price = foodItems.get(i).getPrice();
            this.totalPrice += price;
        }
    }

    public String getId(){
        return id;
    }

    public String getTime(){
        return time;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public ArrayList<Food> getFoodItems(){
        return foodItems;
    }

    public String getCustomerName(){
        return customerName;
    }

    @Override
    public String toString(){
        String orderInformation = "Order: Date: "+time+" Customer: "+customerName+" Price: "+totalPrice;
        return orderInformation;
    }

}
