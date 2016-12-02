package com.restuarants.smart.speechtotextwatsonv7;
import android.content.Intent;
import android.media.MediaPlayer;
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
import java.util.concurrent.ExecutionException;

/**
 * This is the first activity that gets launched when you run this program.
 * MainActivity will be split into two different sections.
 * First section is for customers that do not wish to use the feature of Speech to Text aka Order
 * by speaking. Therefore the user's must navigate through the menu @see MenuActivity and order
 * through there.
 *
 * @author Oscar I. Ricaud
 * @version 1.0 November 23 2017
 */
public class MainActivity extends AppCompatActivity {
    private String rawText = "";
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

    private static final String LOG_TAG = "StT";
    private MediaPlayer mPlayer;
    private RecordWavMaster mic;
    private String outputFilePath;
    private boolean isRecording = false;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.loadingCircle).setVisibility(View.INVISIBLE); // Hide loading circle
        mic = new RecordWavMaster();
        initialCall(); // Call Watson to Welcome the customer
        setUpButtons();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void initialCall() {
        String initialText = "Hello welcome to smart restaurants!";
        TextToSpeechActivity tts = new TextToSpeechActivity(getApplicationContext());
        tts.execute(initialText);
    //    ConversationExample ce = new ConversationExample();
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

    private String convertRawTextFromJson(String rawText) {
        return rawText;
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
        protected void onProgressUpdate(Integer... progress) {
        }

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

                        // System.out.println("final text " + finalText); // PRINTS IN JSON format
                        TextToSpeechActivity tts = new TextToSpeechActivity(getApplicationContext());
                        tts.execute("Did you say " + transcript);

                        //  Start having a conversation with Watson, if yes open activity.
                        //  ConversationStart cs = new ConversationStart();
                  /*     ConversationService service = new ConversationService("2016-11-29");
                        service.setUsernameAndPassword("2cd79c69-afac-4288-973a-120db0f1efef", "ENFhhydOkd5q");
                        MessageRequest newMessage = new MessageRequest.Builder().inputText("I am hungry.").build();
                        String sillyWorkspaceID = "25dfa8a0-0263-471b-8980-317e68c30488";
                        MessageResponse response = service.message(sillyWorkspaceID, newMessage).execute();
                       System.out.println("here " + response); */
                    }
                }
                try { // Who is your father? Watson shall say I am the father.
                    findIntent("");
                } catch (ExecutionException | InterruptedException e) {
                    System.out.println("not working");
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void findIntent(String transcript) throws ExecutionException, InterruptedException {
        System.out.println("here");
   //     ConversationStart cs = new ConversationStart();
    }
}