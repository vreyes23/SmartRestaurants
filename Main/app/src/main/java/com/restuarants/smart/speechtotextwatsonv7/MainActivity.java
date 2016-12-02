package com.restuarants.smart.speechtotextwatsonv7;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

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
    private String rawText = "";
    private static final String LOG_TAG = "StT";
    private RecordWavMaster mic;
    private String outputFilePath;
    int counter = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    private String convertRawTextFromJson(String rawText) {
        return rawText;
    }

    /** First method that is called when the user runs this app.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.loadingCircle).setVisibility(View.INVISIBLE); // Hide loading circle
        mic = new RecordWavMaster();
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
                if (counter == 0) {
                    System.out.println("counter == 0 ");
                    mic.recordWavStart();
                    //startRecording();
                    findViewById(R.id.loadingCircle).setVisibility(View.VISIBLE);
                    findViewById(R.id.textView9).setVisibility(View.INVISIBLE);
                    speakOrderButton.setText("Stop Recording...");
                }
                if (counter == 1) {
                    System.out.println("counter == 1 ");
                    speakOrderButton.setText("Speak Order");
                    outputFilePath = mic.recordWavStop();
                    //stopRecording();
                    new SpeechToTextTask().execute(""); // Do the magic and convert audio to a string.
                    counter = 0;
                } else {
                    counter++;
                }
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

    private class SpeechToTextTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            SpeechToText service = new SpeechToText();
            service.setUsernameAndPassword("b535f60e-dae2-4783-9e89-5ecaf85a468c", "UqbobaXOFPLX");
            try {
                SpeechResults speechResults = service.recognize(new File(outputFilePath)).execute();
                setRawText(speechResults.toString());
                Log.e(LOG_TAG, String.valueOf(speechResults.getResultIndex()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
        protected void onProgressUpdate(Integer... progress) {}

        protected void onPostExecute(String result) {
            System.out.println("On Post Execute");
            getRawText(); // get customers order
            // Convert from JSON to regular string.
            String finalText = convertRawTextFromJson(getRawText());
            try {
                JSONObject json = new JSONObject(finalText);
                JSONArray results = json.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject jsonas = results.getJSONObject(i);
                    JSONArray results2 = jsonas.getJSONArray("alternatives");
                    String alternatives = jsonas.getString("alternatives");
                    System.out.println("alternatives " + alternatives);

                    for (int j = 0; j < results2.length(); j++) {
                        JSONObject jsonasb = results2.getJSONObject(i);
                        String transcript = jsonasb.getString("transcript");
                        System.out.println("transcript " + transcript);
                        TextView placeHolderText = (TextView) findViewById(R.id.textView9);
                        placeHolderText.setText(transcript);
                        findViewById(R.id.loadingCircle).setVisibility(View.INVISIBLE);
                        findViewById(R.id.textView9).setVisibility(View.VISIBLE); // show text bottom

                        // System.out.println("final text " + finalText); // Debugging, prints Json format
                        TextToSpeechActivity tts = new TextToSpeechActivity(getApplicationContext());
                        tts.execute("Did you say " + transcript);
                        // Send User input and launch @see ConversationActivity
                        Intent convo_intent = new Intent(MainActivity.this, ConversationActivity.class);
                        String user_input = transcript;
                        convo_intent.putExtra("user_input", user_input); // YOUR key, variable you are passing
                        startActivity(convo_intent);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}