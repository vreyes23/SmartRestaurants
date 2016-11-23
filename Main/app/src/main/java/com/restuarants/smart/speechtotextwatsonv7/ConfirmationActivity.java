package com.restuarants.smart.speechtotextwatsonv7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("my_variable");
        }
    }
}
