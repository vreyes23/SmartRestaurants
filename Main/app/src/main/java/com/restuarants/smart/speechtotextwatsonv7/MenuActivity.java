package com.restuarants.smart.speechtotextwatsonv7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

/**
 * The purpose of the MenuActivity is to allow users that do not wish to speak to watson to order
 * their food. These Class is mainly used for old school people just playing.
 * In this class the user can click on the buttons to order the items it wants and then it takes the
 * user @see ConfirmationActivity to confirm their order.
 * @author Oscar I. Ricaud
 * @version 1.0 November 23 2016
 */

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        displayContent();
    }
    private void displayContent() {
        final  DecimalFormat form = new DecimalFormat("0.00");
        // Double Double
        final TextView doubledouble_price = (TextView) findViewById(R.id.price_2_95);
        final TextView qtyDouble = (TextView) findViewById(R.id.etQtyDouble);
        final Button doubledouble_button = (Button) findViewById(R.id.etDouble_double);

        // Cheese Burger
        final TextView cheeseburger_price = (TextView) findViewById(R.id.price_1_95);
        final TextView qtyCheese = (TextView) findViewById(R.id.etQtyCheese);
        final Button cheeseburger_button = (Button) findViewById(R.id.etCheese_burger);

        // Hamburger
        final TextView hamburger_price = (TextView) findViewById(R.id.price_1_65);
        final TextView qtyHam = (TextView) findViewById(R.id.etQtyHam);
        final Button hamburger_button = (Button) findViewById(R.id.etHamburger);

        // Fries
        final TextView fries_price = (TextView) findViewById(R.id.price_1_19);
        final TextView qtyFrie = (TextView) findViewById(R.id.etQtyFrench);
        final Button fries_button = (Button) findViewById(R.id.etFrench_fries);

        // Small Drinks
        final TextView small_price = (TextView) findViewById(R.id.price_1_15);
        final TextView qtySmall = (TextView) findViewById(R.id.etQtySmall);
        final Button small_button = (Button) findViewById(R.id.small);

        // Medium Drinks
        final TextView medium_price = (TextView) findViewById(R.id.price_1_25);
        final TextView qtyMedium = (TextView) findViewById(R.id.etQtyMedium);
        final Button medium_button = (Button) findViewById(R.id.medium);

        // Large Drinks
        final TextView large_price = (TextView) findViewById(R.id.price_1_45);
        final TextView qtyLarge = (TextView) findViewById(R.id.etQtyLarge);
        final Button large_button = (Button) findViewById(R.id.large);

        // Big Boy Drinks
        final TextView xl_price = (TextView) findViewById(R.id.price_1_65_2);
        final TextView qtyXLarge = (TextView) findViewById(R.id.etQtyXLG);
        final Button xl_button = (Button) findViewById(R.id.x_large);

        // Obtain Textview id
        final TextView placeholder = (TextView) findViewById(R.id.totalPricePlaceholder);

        // Place Order Button
        Button placeOrderButton = (Button) findViewById(R.id.etPlaceOrder);
        final CustomerOrder order = new CustomerOrder();

        // Increment price double double when user presses button
        doubledouble_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double incrementor_price = 2.95; // Change the price to this when the user presses the button
            double price_final = 2.95; // this variable is sent to the @see CustomerOrder
            @Override
            public void onClick(View view){
                doubledouble_price.setText(form.format(incrementor_price));
                qtyDouble.setText(String.valueOf(qty));
                order.setQty_from_main_menu(qty);
                order.store_food_item("double-double");
                double temp = order.setItem_from_main_menu(price_final);
                placeholder.setText(form.format(temp)); // For total value on the bottom right.
                qty++;
                incrementor_price = incrementor_price + 2.95;
            }
        });

        // Increment price cheese burger when user presses button
        cheeseburger_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double incrementor_price = 1.95; // Change the price to this when the user presses the button
            double price_final = 1.95; // this variable is sent to the @see CustomerOrder
            @Override
            public void onClick(View view){
                cheeseburger_price.setText(form.format(incrementor_price));
                qtyCheese.setText(String.valueOf(qty));
                order.store_food_item("cheese-burger");
                double temp = order.setItem_from_main_menu(price_final);
                placeholder.setText(form.format(temp)); // For total value on the bottom right.
                qty++;
                incrementor_price = incrementor_price + 1.95;
            }
        });

        // Increment price hamburger when user presses button
        hamburger_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double incrementor_price = 1.65; // Change the price to this when the user presses the button
            double price_final = 1.65; // this variable is sent to the @see CustomerOrder
            @Override
            public void onClick(View view){
                hamburger_price.setText(form.format(incrementor_price));
                qtyHam.setText(String.valueOf(qty));
                order.store_food_item("ham-burger");
                double temp = order.setItem_from_main_menu(price_final);
                placeholder.setText(form.format(temp)); // For total value on the bottom right.
                qty++;
                incrementor_price = incrementor_price + 1.65;
            }
        });

        // Increment price of fries when user presses button
        fries_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double incrementor_price = 1.19; // Change the price to this when the user presses the button
            double price_final = 1.19; // this variable is sent to the @see CustomerOrder
            @Override
            public void onClick(View view){
                fries_price.setText(form.format(incrementor_price));
                qtyFrie.setText(String.valueOf(qty));
                order.store_food_item("fries");
                double temp = order.setItem_from_main_menu(price_final);
                placeholder.setText(form.format(temp)); // For total value on the bottom right.
                qty++;
                incrementor_price = incrementor_price + 1.19;
            }
        });

        // Increment price of small drinks when user presses button
        small_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double incrementor_price = 1.15; // Change the price to this when the user presses the button
            double price_final = 1.15; // this variable is sent to the @see CustomerOrder
            @Override
            public void onClick(View view){
                small_price.setText(form.format(incrementor_price));
                qtySmall.setText(String.valueOf(qty));
                order.store_food_item("Small Drink");
                double temp = order.setItem_from_main_menu(price_final);
                placeholder.setText(form.format(temp)); // For total value on the bottom right.
                qty++;
                incrementor_price = incrementor_price + 1.15;
            }
        });

        // Increment price of medium drinks when user presses button
        medium_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double incrementor_price = 1.25; // Change the price to this when the user presses the button
            double price_final = 1.25; // this variable is sent to the @see CustomerOrder
            @Override
            public void onClick(View view){
                medium_price.setText(form.format(incrementor_price));
                qtyMedium.setText(String.valueOf(qty));
                order.store_food_item("Medium Drink");
                double temp = order.setItem_from_main_menu(price_final);
                placeholder.setText(form.format(temp)); // For total value on the bottom right.
                qty++;
                incrementor_price = incrementor_price + 1.25;
            }
        });

        // Increment price large drinks when user presses button
        large_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double incrementor_price = 1.45; // Change the price to this when the user presses the button
            double price_final = 1.55; // this variable is sent to the @see CustomerOrder
            @Override
            public void onClick(View view){
                large_price.setText(form.format(incrementor_price));
                qtyLarge.setText(String.valueOf(qty));
                order.store_food_item("Large Drink");
                double temp = order.setItem_from_main_menu(price_final);
                placeholder.setText(form.format(temp)); // For total value on the bottom right.
                qty++;
                incrementor_price = incrementor_price + 1.45;
            }
        });

        // Increment price for big-boy drinks when user presses button
        xl_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double incrementor_price = 1.65; // Change the price to this when the user presses the button
            double price_final = 1.65; // this variable is sent to the @see CustomerOrder
            @Override
            public void onClick(View view){
                xl_price.setText(form.format(incrementor_price));
                qtyXLarge.setText(String.valueOf(qty));
                order.store_food_item("X-LG Drink");
                double temp = order.setItem_from_main_menu(price_final);
                placeholder.setText(form.format(temp)); // For total value on the bottom right.
                qty++;
                incrementor_price = incrementor_price + 1.65;
            }
        });

        // Place Order
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Prints price bottom right corner on the next activity
                Intent intent = new Intent(MenuActivity.this, ConfirmationActivity.class);
                String receipt_price = String.valueOf(order.getItem_from_main_menu());
                intent.putExtra("receipt_price", receipt_price); // YOUR key, variable you are passing

                // Prints item in the center of the next activity
                String receipt_item = String.valueOf(order.get_list_items());
                intent.putExtra("receipt_item", receipt_item);
                startActivity(intent);
            }
        });
    }
}
