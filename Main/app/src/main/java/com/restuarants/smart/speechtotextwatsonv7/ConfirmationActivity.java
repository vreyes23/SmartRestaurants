package com.restuarants.smart.speechtotextwatsonv7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        final TextView totalPlaceholder = (TextView) findViewById(R.id.etTotalConfirmation);
        final TextView receiptItems = (TextView) findViewById(R.id.etPlaceHolderConfirmation);
        final Button confirm_button = (Button) findViewById(R.id.etConfirmButton);
        // Receiving total price from @see MenuActivity
        Bundle extras = getIntent().getExtras();
        String receiptTotal = extras.getString("receipt_price"); // Look for YOUR KEY, variable you're receiving
        totalPlaceholder.setText("$" + receiptTotal);

        // Receiving items from @see MenuActivity
        String receiptItem = extras.getString("receipt_item"); // Look for YOUR KEY, variable you're receiving
        receiptItems.setText(receiptItem); // Prints the items the user order on the Confirmation Activity
        // Set ticket number
        Random rand = new Random();
        int  n = rand.nextInt(500) + 1;
        final TextView ticketID = (TextView) findViewById(R.id.etticketID);
        ticketID.setText(String.valueOf(n));

        // Send data to the database.
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerDB ticket_order = new CustomerDB();

                ticket_order.setFirstName("");
            }
        });
    }
}
