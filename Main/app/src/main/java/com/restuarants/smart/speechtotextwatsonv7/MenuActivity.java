package com.restuarants.smart.speechtotextwatsonv7;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        // Obtain textview id
        final TextView placeholder = (TextView) findViewById(R.id.totalPricePlaceholder);

        // Place Order
        Button placeOrderButton = (Button) findViewById(R.id.etPlaceOrder);

        final CustomerOrder order = new CustomerOrder();


        // Increment price double double when user presses button
        doubledouble_button.setOnClickListener(new View.OnClickListener() {
            int qty = 1;
            double price = 5.90; // Change the price to this when the user presses the button
            @Override
            public void onClick(View view){
                doubledouble_price.setText(form.format(price));
                qtyDouble.setText(String.valueOf(qty));
                order.setQty_from_main_menu(qty);
                placeholder.setText(form.format(price)); // For total value on the bottom right.
                qty++;
                price = price + 2.95;
            }
        });

        // Increment price cheese burger when user presses button
        cheeseburger_button.setOnClickListener(new View.OnClickListener() {
            int qty = Integer.parseInt(("2"));
            double price = Double.parseDouble(("3.90"));
            @Override
            public void onClick(View view){
                cheeseburger_price.setText("" + price);
                qtyCheese.setText("" + qty);
                qty = qty + 1;
                price = price + 1.95;
            }
        });

        // Increment price hamburger when user presses button
        hamburger_button.setOnClickListener(new View.OnClickListener() {
            int qty = Integer.parseInt(("2"));
            double price = Double.parseDouble(("3.30"));
            @Override
            public void onClick(View view){
                hamburger_price.setText("" + price);
                qtyHam.setText("" + qty);
                qty = qty + 1;
                price = price + 1.65;
            }
        });

        // Increment price fries when user presses button
        fries_button.setOnClickListener(new View.OnClickListener() {
            int qty = Integer.parseInt(("2"));
            double price = Double.parseDouble(("2.38"));
            @Override
            public void onClick(View view){
                fries_price.setText("" + price);
                qtyFrie.setText("" + qty);
                qty = qty + 1;
                price = price + 1.19;
            }
        });

        // Increment price small drinks when user presses button
        small_button.setOnClickListener(new View.OnClickListener() {
            int qty = Integer.parseInt(("2"));
            double price = Double.parseDouble(("2.30"));
            @Override
            public void onClick(View view){
                small_price.setText("" + price);
                qtySmall.setText("" + qty);
                qty = qty + 1;
                price = price + 1.15;
            }
        });

        // Increment price medium drinks when user presses button
        medium_button.setOnClickListener(new View.OnClickListener() {
            int qty = Integer.parseInt(("2"));
            double price = Double.parseDouble(("2.50"));
            @Override
            public void onClick(View view){
                medium_price.setText("" + price);
                qtyMedium.setText("" + qty);
                qty = qty + 1;
                price = price + 1.25;
            }
        });

        // Increment price large drinks when user presses button
        large_button.setOnClickListener(new View.OnClickListener() {
            int qty = Integer.parseInt(("2"));
            double price = Double.parseDouble(("2.90"));
            @Override
            public void onClick(View view){
                large_price.setText("$" + price);
                qtyLarge.setText("" + qty);
                qty = qty + 1;
                price = price + 1.45;
            }
        });

        // Increment price for big-boy drinks when user presses button
        xl_button.setOnClickListener(new View.OnClickListener() {
            int qty = Integer.parseInt(("2"));
            double price = Double.parseDouble(("3.30"));
            @Override
            public void onClick(View view){
                xl_price.setText("" + price);
                qtyXLarge.setText("" + qty);
                qty = qty + 1;
                price = price + 1.65;
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
