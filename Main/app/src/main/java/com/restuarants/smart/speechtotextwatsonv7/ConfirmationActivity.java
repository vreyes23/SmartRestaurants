package com.restuarants.smart.speechtotextwatsonv7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DecimalFormat form = new DecimalFormat("0.00");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        final TextView totalPlaceholder = (TextView) findViewById(R.id.etTotalConfirmation);

        // Receiving data from @see MenuActivity
        Bundle extras = getIntent().getExtras();
        String receiptTotal = extras.getString("receipt"); // Look for YOUR KEY, variable you're receiving
        totalPlaceholder.setText(receiptTotal);
    }
}
