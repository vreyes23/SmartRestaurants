package com.restuarants.smart.speechtotextwatsonv7;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.ibm.watson.developer_cloud.speech_to_text.v1.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import java.io.File;
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

    private static final String LOG_TAG = "StT";
    private MediaPlayer mPlayer;
    private RecordWavMaster rwm;
    private String outputFilePath;
    private boolean isRecording = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rwm = new RecordWavMaster();
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
        final Button speakOrderButton = (Button) findViewById(R.id.etspeakOrder);
        speakOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordButtonPressed(speakOrderButton); // Start listening.
                new SpeechToTextTask().execute(""); // Do the magic and convert audio to a string.
                /*
                Intent intent = new Intent(MainActivity.this, SpeakOrder.class);
                MainActivity.this.startActivity(intent);*/
            }
        });
    }

    private void recordButtonPressed(Button speakOrderButton) {
            if (isRecording) {
                outputFilePath = rwm.recordWavStop();
                //stopRecording();
                speakOrderButton.setText("Start Recording");
            } else {
                rwm.recordWavStart();
                //startRecording();
                speakOrderButton.setText("Stop Recording...");
            }
            isRecording = !isRecording;
    }

    private class SpeechToTextTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params) {
            long totalSize = 0;
            SpeechToText service = new SpeechToText();
            service.setUsernameAndPassword("b535f60e-dae2-4783-9e89-5ecaf85a468c", "UqbobaXOFPLX");

            RecognizeOptions options = new RecognizeOptions().contentType("audio/wav");

            try {
                File sdcard = Environment.getExternalStorageDirectory();

                File file = new File(outputFilePath);

                SpeechResults speechResults = service.recognize(file, options);

                Log.e(LOG_TAG, speechResults.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
        protected void onProgressUpdate(Integer... progress) {}

        protected void onPostExecute(String result) {}
    }
}