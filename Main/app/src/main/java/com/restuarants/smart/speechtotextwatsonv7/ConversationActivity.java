package com.restuarants.smart.speechtotextwatsonv7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
 * This class allows the user to interact with watson. It first waits for the user to press the button
 * and once this button is pressed and the RecordWavMaster starts recording the user audio. If the
 * button is pressed again it stops recording and converts this audio to a string. Then @see ConversationTask
 * looks for a corresponding pre-defined intent of the string that was obtained earlier to converse with
 * the user.
 * @author Oscar I. Ricaud
 * @version 1.0.0 December 04 2016
 */
public class ConversationActivity extends AppCompatActivity{
    private String input_from_user = "";
    int counter = 0;
    private String rawText = "";
    private static final String LOG_TAG = "StT";
    private RecordWavMaster mic2;
    private String outputFilePath;
    private Map<String,Object> watsonContext;
    private MessageRequest WatsonMessageRequest;
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
            findViewById(R.id.loadingCircle).setVisibility(View.INVISIBLE); // Hide loading circle
            findViewById(R.id.textView12).setVisibility(View.INVISIBLE); // Hide Recording...
            findViewById(R.id.textView13).setVisibility(View.INVISIBLE); // Hide please wait...
            mic2 = new RecordWavMaster();
            // Receiving total price from @see MenuActivity2
      /*      Bundle extras = getIntent().getExtras();
            String userInput = extras.getString("user_input"); // Look for YOUR KEY, variable you're receiving
            userInputPlaceholder.setText(userInput);
            setInput_from_user(userInput); */
            setUpButtons();
        }

    /**
     * This is method the most important section of this class because it allows the user to send
     * multiple instances with Watson Conversation. When the button is pressed it records
     * audio. If the user presses the button again it stops recording and tells the @see SpeechToTestTask
     * to convert this audio to a String. Then @see ConversationTask looks for the corresponding intent
     * based on the specific String that was obtained at @see SpeechToTestTask. In conclusion the button
     * allows for the user to keep interacting with watson.
     */
    private void setUpButtons() {
        final ImageButton pressButton = (ImageButton) findViewById(R.id.press_button_conversation);
        pressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 0) {
                    System.out.println("Button pressed once");
                    mic2.recordWavStart();
                    findViewById(R.id.loadingCircle).setVisibility(View.VISIBLE); // Hide loading circle
                    findViewById(R.id.textView12).setVisibility(View.VISIBLE); // Hide recording...
                   // pressButton.setText("Stop Recording...");
                }
                // Start convo with Watson
                if (counter == 1) {
                  //  pressButton.setText("Speak Order");
                    findViewById(R.id.loadingCircle).setVisibility(View.INVISIBLE); // Hide loading circle
                    findViewById(R.id.textView12).setVisibility(View.INVISIBLE); // Hide recording=
                    outputFilePath = mic2.recordWavStop();
                    findViewById(R.id.textView13).setVisibility(View.INVISIBLE); // View please wait...
                    //stopRecording();
                    new SpeechToTextTask().execute(""); // Do the magic and convert audio to a string.
                    new ConversationTask().execute("");
                    counter = 0;
                }
                else {
                    counter++;
                }
            }
        });
    }

    public Map<String, Object> getWatsonContext() {
        return watsonContext;
    }

    public void setWatsonContext(Map<String, Object> watsonContext) {
        this.watsonContext = watsonContext;
    }

    public MessageRequest getWatsonMessageRequest() {
        return WatsonMessageRequest;
    }

    public void setWatsonMessageRequest(MessageRequest watsonMessageRequest) {
        WatsonMessageRequest = watsonMessageRequest;
    }
    /**
     * This class calls the front end conversation tree I created on IBM's website. The conversation
     * starts at the root and waits for the user input to find the corresponding intent that has
     * been already defined. A scenario could be In-N-Out customers could say the following:
     * "I would want to order a double double." Watson immediately finds the intent, #double_double
     * and repeats what the intent has defined. In this case Watson would replied with "The double
     * double includes lettuce, tomato, spread with or without onions and stacked high on a freshly
     * baked bun. Is this what you would like to order?" The user could reply with Yes or No and repeats
     * the process, This class looks for the corresponding intent.
     * @author Oscar I. Ricaud
     * @version 1.0.0 December 05 2016
     */
    private class ConversationTask extends AsyncTask<String, Void, String> {
            protected String doInBackground(String... strings) {
                System.out.println("do in background conversation start");
                final ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
                service.setUsernameAndPassword("2cd79c69-afac-4288-973a-120db0f1efef", "ENFhhydOkd5q");
                String orderWorkspaceID = "ce288cad-6cd5-450f-8225-01216386761e";
                // If context is empty, where context means if there has been an instance of the user
                // interacting with Watson
                if(getWatsonContext() == null){
                    System.out.println("Context is empty therefore start a new conversation with Watson. ");
                    MessageRequest newMessage = new MessageRequest.Builder().inputText(getInput_from_user()).build();
                    MessageResponse response = service.message(orderWorkspaceID, newMessage).execute();
                    Map<String, Object> context = response.getContext();
                    System.out.println("context " + context);
                    setWatsonContext(context);
                    setWatsonMessageRequest(newMessage);
                } else {  // There has been an instance with the user interacting with Watson, continue
                        // Conversation with user.
                        System.out.println("Context is not empty therefore continue conversation with user ");
                        MessageRequest messageContext = new MessageRequest.Builder().inputText(getInput_from_user()).context(getWatsonContext()).build();
                        MessageResponse messageContextResponse = service.message(orderWorkspaceID, messageContext).execute();
                        System.out.println("messageContextResponse " + messageContextResponse);
                        Map<String, Object> curr_context = messageContextResponse.getContext();
                        System.out.println("curr_context " + curr_context);
                        setWatsonContext(curr_context);
                        setWatsonMessageRequest(messageContext);
                }
                // rx async callback
                service.message(orderWorkspaceID, getWatsonMessageRequest()).rx().thenApplyAsync(new CompletableFuture.Fun<MessageResponse, Map<String, Object>>() {
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
                                findViewById(R.id.loadingCircle).setVisibility(View.INVISIBLE); // Hide loading circle
                                findViewById(R.id.textView12).setVisibility(View.INVISIBLE); // Hide Recording...
                            }
                        }
                    }
                });
                MessageResponse rxMessageResponse = null;
                try {
                    rxMessageResponse = service.message(orderWorkspaceID, getWatsonMessageRequest()).rx().get();
                    System.out.println("rxRESPONSE" + rxMessageResponse);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }
        protected void onPostExecute(String result) {
            System.out.println("ON POST EXECUTE CONVO");
            TextView userInputPlaceholder = (TextView) findViewById(R.id.textView10); // prints user input
            userInputPlaceholder.setText(getInput_from_user());
        }
    }
    /**
     * This class verifies the services username and password and allows to take into the argument
     * an Audio file and converts this audio file into text.
     * @author Oscar I. Ricaud
     * @version 1.0.0 12/04/2016
     */
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
