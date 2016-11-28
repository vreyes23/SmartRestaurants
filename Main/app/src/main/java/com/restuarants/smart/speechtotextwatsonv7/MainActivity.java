package com.restuarants.smart.speechtotextwatsonv7;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
/**
 * This is the first activity that gets launched when you run this program.
 * MainActivity will be split into two different sections.
 * First section is for customers that do not wish to use the feature of Speech to Text aka Order
 * by speaking. Therefore the user's must navigate through the menu @see MenuActivity and order
 * through there.
 * @author Oscar I. Ricaud
 * @version 1.0 November 23 2017
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpButtons();
        initialCall(); // Call Watson to Welcome the customer
    }

    public void initialCall(){
        String initialText = "Hello welcome to smart restaurants. You can press the top left button to manually order the food. Or you can press the top right button to tell me your order.";
        TextToSpeech tts = new TextToSpeech(getApplicationContext());
        tts.execute(initialText);
    }

    private void setUpButtons() {
        // Left top button, order if you're old school.
        Button menuButton = (Button) findViewById(R.id.etMenu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        // Right top button, order if you're ready for the future. 
        Button speakOrderButton = (Button) findViewById(R.id.etspeakOrder);
        speakOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpeakOrder.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}