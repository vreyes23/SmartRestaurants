package com.restuarants.smart.speechtotextwatsonv7;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class gets called from @see MenuActivity, in other words this will be used as a pre-confirmation
 * of a receipt. It is common for the cashier to repeat what the customer order to minimize ambiguity
 * between the short conversation. Therefore if there order is correct we can use this class to send
 * the data to the cook.
 * @author Oscar Ricaud
 * @version 1.0 November 23 2016
 */
public class CustomerOrder {

    public CustomerOrder() {
    }

    private int qty_from_main_menu = 0;


    public int getQty_from_main_menu() {
        return qty_from_main_menu;
    }

    public void setQty_from_main_menu(int qty_from_main_menu) {
        this.qty_from_main_menu = qty_from_main_menu;
    }
}
