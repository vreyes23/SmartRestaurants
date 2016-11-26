package com.restuarants.smart.speechtotextwatsonv7;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * This class gets called from @see MenuActivity, in other words this will be used as a pre-confirmation
 * of a receipt. It is common for the cashier to repeat what the customer order to minimize ambiguity
 * between the short conversation. Therefore if there order is correct we can use this class to send
 * the data to the cook.
 * @author Oscar Ricaud
 * @version 1.0 November 23 2016
 */
class CustomerOrder implements Serializable{

    CustomerOrder() {}
    private int qty_from_main_menu = 0;
    private double holder = 0.0;
    private LinkedList<String> food_items = new LinkedList<String>();

    public int getQty_from_main_menu() {
        return qty_from_main_menu;
    }

    void setQty_from_main_menu(int qty_from_main_menu) {
        this.qty_from_main_menu = qty_from_main_menu;
    }

    // Gets the total prices of the items and sends it to the @see Confirmation activity
    double getItem_from_main_menu() {
        return holder;
    }

    // Gets the prices of the items the user/customer orders from. This method acts as
    // a way to store data.
    double setItem_from_main_menu(double currPrice) {
        holder = holder + currPrice;
        return holder;
    }
    void store_food_item(String currItem){
        food_items.add(currItem);
    }
    String get_list_items(){
        return food_items.toString();
    }
}
