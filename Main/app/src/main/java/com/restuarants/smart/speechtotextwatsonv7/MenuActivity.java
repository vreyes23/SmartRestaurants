package com.restuarants.smart.speechtotextwatsonv7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        // Double Double
        final TextView doubledouble_price = (TextView) findViewById(R.id.price_2_95);
        final Button doubledouble_button = (Button) findViewById(R.id.etDouble_double);

        // Cheese Burger
        final TextView cheeseburger_price = (TextView) findViewById(R.id.price_1_95);
        final Button cheeseburger_button = (Button) findViewById(R.id.etCheese_burger);

        // Hamburger
        final TextView hamburger_price = (TextView) findViewById(R.id.price_1_65);
        final Button hamburger_button = (Button) findViewById(R.id.etHamburger);

        // Hamburger
        final TextView fries_price = (TextView) findViewById(R.id.price_1_19);
        final Button fries_button = (Button) findViewById(R.id.etFrench_fries);

        // Small Drinks
        final TextView small_price = (TextView) findViewById(R.id.price_1_15);
        final Button small_button = (Button) findViewById(R.id.small);

        // Medium Drinks
        final TextView medium_price = (TextView) findViewById(R.id.price_1_25);
        final Button medium_button = (Button) findViewById(R.id.medium);

        // Large Drinks
        final TextView large_price = (TextView) findViewById(R.id.price_1_45);
        final Button large_button = (Button) findViewById(R.id.large);

        // Big Boy Drinks
        final TextView xl_price = (TextView) findViewById(R.id.price_1_65_2);
        final Button xl_button = (Button) findViewById(R.id.x_large);

        // Place Order
        Button placeOrderButton = (Button) findViewById(R.id.etPlaceOrder);

        // Increment price double double when user presses button
        doubledouble_button.setOnClickListener(new View.OnClickListener() {

            double price = Double.parseDouble(("5.90"));
            @Override
            public void onClick(View view){
                doubledouble_price.setText("$" + price);
                price = price + price;
            }
        });

        // Increment price cheese burger when user presses button
        cheeseburger_button.setOnClickListener(new View.OnClickListener() {
            double price = Double.parseDouble(("3.90"));
            @Override
            public void onClick(View view){
                cheeseburger_price.setText("$" + price);
                price = price + price;
            }
        });

        // Increment price hamburger when user presses button
        hamburger_button.setOnClickListener(new View.OnClickListener() {
            double price = Double.parseDouble(("3.30"));
            @Override
            public void onClick(View view){
                hamburger_price.setText("$" + price);
                price = price + price;
            }
        });

        // Increment price fries when user presses button
        fries_button.setOnClickListener(new View.OnClickListener() {
            double price = Double.parseDouble(("2.38"));
            @Override
            public void onClick(View view){
                fries_price.setText("$" + price);
                price = price + price;
            }
        });

        // Increment price small drinks when user presses button
        small_button.setOnClickListener(new View.OnClickListener() {
            double price = Double.parseDouble(("2.30"));
            @Override
            public void onClick(View view){
                small_price.setText("$" + price);
                price = price + price;
            }
        });

        // Increment price medium drinks when user presses button
        medium_button.setOnClickListener(new View.OnClickListener() {
            double price = Double.parseDouble(("2.50"));
            @Override
            public void onClick(View view){
                medium_price.setText("$" + price);
                price = price + price;
            }
        });

        // Increment price large drinks when user presses button
        large_button.setOnClickListener(new View.OnClickListener() {
            double price = Double.parseDouble(("2.90"));
            @Override
            public void onClick(View view){
                large_price.setText("$" + price);
                price = price + price;
            }
        });

        // Increment price for big-boy drinks when user presses button
        xl_button.setOnClickListener(new View.OnClickListener() {
            double price = Double.parseDouble(("3.30"));
            @Override
            public void onClick(View view){
                xl_price.setText("$" + price);
                price = price + price;
            }
        });

        // Place Order
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ConfirmationActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });

    }

    public void onToggleClicked(View view) {
    }
}
