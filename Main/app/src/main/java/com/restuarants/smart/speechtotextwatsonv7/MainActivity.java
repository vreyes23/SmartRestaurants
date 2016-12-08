package com.restuarants.smart.speechtotextwatsonv7;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * This is the first activity that gets launched when you run this program.
 * MainActivity will be split into two different sections.
 * First section, @see MenuActivity, this section is for customers that do not wish to use the
 * feature of Speech to Text aka Order by speaking.
 * Therefore the user's must navigate through the menu @see MenuActivity and manually order through there.
 * @author Oscar I. Ricaud
 * @version 1.0 December 2nd 2017
 */
public class MainActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /** First method that is called when the user runs this app.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.loadingCircle).setVisibility(View.INVISIBLE); // Hide loading circle
        initialCall();
        setUpButtons();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * In this method we tell Watson to speak to the customer
     */
    public void initialCall() {
        String initialText = "Hello welcome to smart restaurants!";
        TextToSpeechActivity tts = new TextToSpeechActivity(getApplicationContext());
        tts.execute(initialText);
    //    ConversationExample ce = new ConversationExample();
    }

    /**
     * This method sets up the buttons for the user including the the View Menu and Speak Order Button.
     */
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
        final Button speakOrderButton = (Button) findViewById(R.id.etspeakOrder);
        speakOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConversationActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}