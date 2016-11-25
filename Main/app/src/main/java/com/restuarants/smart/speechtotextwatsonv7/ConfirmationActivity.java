package com.restuarants.smart.speechtotextwatsonv7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        final TextView totalPlaceholder = (TextView) findViewById(R.id.etTotalConfirmation);
        final TextView receiptItems = (TextView) findViewById(R.id.etPlaceHolderConfirmation);

        // Receiving total price from @see MenuActivity
        Bundle extras = getIntent().getExtras();
        String receiptTotal = extras.getString("receipt_price"); // Look for YOUR KEY, variable you're receiving
        totalPlaceholder.setText("$" + receiptTotal);

        // Receiving items from @see MenuActivity
        String receiptItem = extras.getString("receipt_item"); // Look for YOUR KEY, variable you're receiving
        receiptItems.setText(receiptItem); // Prints the items the user order on the Confirmation Activity
    }
}
