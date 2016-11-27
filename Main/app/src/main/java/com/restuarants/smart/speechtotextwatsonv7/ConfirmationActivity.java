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
 * @author Oscar Ricaud
 * @version 1.0 Last update 11/26/2016
 */
public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        final TextView totalPlaceholder = (TextView) findViewById(R.id.etTotalConfirmation);
        final TextView receiptItems = (TextView) findViewById(R.id.etPlaceHolderConfirmation);
        final Button confirm_button = (Button) findViewById(R.id.etConfirmButton);
        // Receiving total price from @see MenuActivity2
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

        // Get watson to say the total price
        initialCall(receiptTotal, receiptItem);
        // Send data to the database @see ReceiptDatabase class.
        confirm_button.setOnClickListener(new View.OnClickListener() {
            // Get users name
            final EditText etFirstname = (EditText) findViewById(R.id.firstname);
            @Override
            public void onClick(View view) {
                final String firstname = etFirstname.getText().toString();

                ReceiptDatabase db = new ReceiptDatabase();
                db.setName(firstname);
                db.setTicketnumber(String.valueOf(n));
                db.setFoodItems(receiptItem);

                // Go to the final activity
                Intent intent = new Intent(ConfirmationActivity.this, ThankyouActivity.class);
                ConfirmationActivity.this.startActivity(intent);
            }
        });
    }

    private void initialCall(String receiptTotal, String receiptItem) {

        String what_to_say = "Your order is " + receiptItem + " and your total is " + receiptTotal + " " +
                "dollars. Would you like to pay" + "cash or credit.";
        TextToSpeech tts = new TextToSpeech(getApplicationContext());
        tts.execute(what_to_say);
    }
}
