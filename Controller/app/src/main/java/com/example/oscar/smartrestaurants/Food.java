package com.example.oscar.smartrestaurants;

/**
 * Created by Oscar on 10/8/16.
 */
public class Food {

    private String food_id;
    private String name;
    private Double price;
    private Boolean prepared;
    private Boolean served;

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getPrepared() {
        return prepared;
    }

    public void setPrepared(Boolean prepared) {
        this.prepared = prepared;
    }

    public Boolean getServed() {
        return served;
    }

    public void setServed(Boolean served) {
        this.served = served;
    }
    public void setFood(){

    }
}
