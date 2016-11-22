package com.restuarants.smart.speechtotextwatsonv7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button placeOrderButton = (Button) findViewById(R.id.etPlaceOrder);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, Confirmation.class);
                MenuActivity.this.startActivity(intent);
            }
        });
    }

    public void onToggleClicked(View view) {
    }
}
