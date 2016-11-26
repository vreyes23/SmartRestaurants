package com.restuarants.smart.speechtotextwatsonv7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/** The purpose of this class is to thank the customer for using our service. Once the food is ready
 * the database will send a notification to the customer that their order is ready through here.
 * @author Oscar Ricaud
 * @version 1.0 Last update 11/26/2016
 */
public class ThankyouActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        // Create method to Retrieve data from the database
        // Create method to receive push notifications
    }
}
