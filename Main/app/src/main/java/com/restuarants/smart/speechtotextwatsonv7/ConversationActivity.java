package com.restuarants.smart.speechtotextwatsonv7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import jersey.repackaged.jsr166e.CompletableFuture;

/**
 * @author Oscar I. Ricaud
 */
public class ConversationActivity extends AppCompatActivity{
    private String input_from_user = "";
    private String whatToSay="";
    int counter = 0;
    private String rawText = "";
    private static final String LOG_TAG = "StT";
    private RecordWavMaster mic2;
    private String outputFilePath;
    private Map<String,Object> watsonContext;
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    private String convertRawTextFromJson(String rawText) {
        return rawText;
    }

    public String getInput_from_user() {
        return input_from_user;
    }
    public void setInput_from_user(String input_from_user) {
        this.input_from_user = input_from_user;
    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_conversation);
            final TextView userInputPlaceholder = (TextView) findViewById(R.id.textView10); // prints user input
            // Receiving total price from @see MenuActivity2
            Bundle extras = getIntent().getExtras();
            String userInput = extras.getString("user_input"); // Look for YOUR KEY, variable you're receiving
            userInputPlaceholder.setText(userInput);
            setInput_from_user(userInput);
            new ConversationTask().execute("");
        }

    private void setUpButton() {

        final Button pressButton = (Button) findViewById(R.id.press_button_conversation);
        pressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 0) {
                    System.out.println("counter == 0 ");
                    mic2.recordWavStart();
                    pressButton.setText("Stop Recording...");
                }
                if (counter == 1) {
                    System.out.println("counter == 1 ");
                    pressButton.setText("Speak Order");
                    outputFilePath = mic2.recordWavStop();
                    //stopRecording();
                    new SpeechToTextTask().execute(""); // Do the magic and convert audio to a string.
                    new ConversationTask().execute("");
                    mic2.releaseRecord();
                    counter = 0;
                } else {
                    counter++;
                }
            }
        });
    }


    private class ConversationTask extends AsyncTask<String, Void, String> {
            protected String doInBackground(String... strings) {
                System.out.println("do in background conversation start");
                final ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
                service.setUsernameAndPassword("2cd79c69-afac-4288-973a-120db0f1efef", "ENFhhydOkd5q");

                // sync
                MessageRequest newMessage = new MessageRequest.Builder().inputText(getInput_from_user()).build();
                String orderWorkspaceID = "ce288cad-6cd5-450f-8225-01216386761e";
                MessageResponse response = service.message(orderWorkspaceID, newMessage).execute();
                Map<String, Object> context = response.getContext();
                System.out.println("context " + context);

                MessageRequest newMessage2 = new MessageRequest.Builder().inputText("yes").context(context).build();
                MessageResponse response2 = service.message(orderWorkspaceID, newMessage2).execute();
                System.out.println("response2 " + response2);
                Map<String, Object> context2 = response2.getContext();
                System.out.println("context2 " + context2);

/*
                MessageRequest newMessage2 = new MessageRequest.Builder().inputText( "yes").build();
                MessageResponse response2 = service.message(orderWorkspaceID, newMessage2).execute();
                System.out.println("response2 " + response2);
*/
                // rx async callback
                service.message(orderWorkspaceID, newMessage).rx().thenApplyAsync(new CompletableFuture.Fun<MessageResponse, Map<String, Object>>() {
                    @Override
                    public Map<String, Object> apply(MessageResponse message) {
                        System.out.println("Context FOUND" + message.getContext());
                        return message.getOutput();
                    }}).thenAccept(new CompletableFuture.Action<Map<String, Object>>() {
                    @Override
                    public void accept(Map<String, Object> output) {
                        for (Map.Entry<String, Object> entry : output.entrySet())
                        {
                            if(entry.getKey().contains("text")) {
                                System.out.println("what watson should say back" + entry.getKey() + "/" + entry.getValue());
                                TextToSpeechActivity tts = new TextToSpeechActivity(getApplicationContext());
                                tts.execute("" + entry.getValue());
                                setInput_from_user("yes");
                                //new ConversationTask().execute("");

                            }
                        }

                    }
                });
                MessageResponse rxMessageResponse = null;
                try {
                    rxMessageResponse = service.message(orderWorkspaceID, newMessage).rx().get();
                    System.out.println("rxRESPONSE" + rxMessageResponse);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
    public class SpeechToTextTask extends AsyncTask<String, Void, String> {
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
                        setInput_from_user(transcript);

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
