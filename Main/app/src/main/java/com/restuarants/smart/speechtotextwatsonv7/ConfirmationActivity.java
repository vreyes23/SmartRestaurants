package com.restuarants.smart.speechtotextwatsonv7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * This activity is the last activity before we send the data to the database, it is important to verify
 * the customers order and make sure the details are correct.
 * I created a random ticket ID which displays a random number of the order
 * I also created a place holder for the user to enter the name and last and not least I included
 * the customers order in a linked list.
 */
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
        final String receiptItem = extras.getString("receipt_item"); // Look for YOUR KEY, variable you're receiving
        receiptItems.setText(receiptItem); // Prints the items the user order on the Confirmation Activity
        // Set ticket number
        Random rand = new Random();
        final int  n = rand.nextInt(500) + 1;
        final TextView ticketID = (TextView) findViewById(R.id.etticketID);
        ticketID.setText(String.valueOf(n));

        // Send data to the database @see DataRetriever class.
        confirm_button.setOnClickListener(new View.OnClickListener() {
            // Get users name
            final EditText etFirstname = (EditText) findViewById(R.id.firstname);

            @Override
            public void onClick(View view) {
                final String firstname = etFirstname.getText().toString();
/*      Send user to final activity, I will complete this later.
                Intent intent = new Intent(ConfirmationActivity.this, DataRetriever.class);
                String final_receipt = "This is a test";
                intent.putExtra("final_receipt", final_receipt);
                startActivity(intent);
*/
                DataRetriever db = new DataRetriever();
                db.setName(firstname);
                db.setTicketnumber(String.valueOf(n));
                db.setFoodItems(receiptItem);




            }
        });
    }
}
