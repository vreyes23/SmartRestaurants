package com.restuarants.smart.speechtotextwatsonv7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import jersey.repackaged.jsr166e.CompletableFuture;

/**
 * @author Oscar I. Ricaud
 */
public class ConversationActivity extends AppCompatActivity{
    private String input_from_user = "";
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

    private class ConversationTask extends AsyncTask<String, Void, String> {
            protected String doInBackground(String... strings) {
                System.out.println("do in background conversation start");
                ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
                service.setUsernameAndPassword("2cd79c69-afac-4288-973a-120db0f1efef", "ENFhhydOkd5q");

                // sync
                MessageRequest newMessage = new MessageRequest.Builder().inputText(getInput_from_user()).build();
                String sillyWorkspaceID = "ce288cad-6cd5-450f-8225-01216386761e";
                MessageResponse response = service.message(sillyWorkspaceID, newMessage).execute();
                System.out.println(response);

                // async
                service.message(sillyWorkspaceID, newMessage).enqueue(new ServiceCallback<MessageResponse>() {
                    @Override
                    public void onResponse(MessageResponse response) {
                        System.out.println("here " + response);
                    }
                    @Override
                    public void onFailure(Exception e) {}
                });

                // rx callback
                service.message(sillyWorkspaceID, newMessage).rx()
                        .thenApply(new CompletableFuture.Fun<MessageResponse, Map<String, Object>>() {
                            @Override
                            public Map<String, Object> apply(MessageResponse message) {
                                return message.getOutput();
                            }
                        }).thenAccept(new CompletableFuture.Action<Map<String, Object>>() {
                    @Override
                    public void accept(Map<String, Object> output) {
                        System.out.println(output);
                    }
                });

                // rx async callback
                service.message(sillyWorkspaceID, newMessage).rx()
                        .thenApplyAsync(new CompletableFuture.Fun<MessageResponse, Map<String, Object>>() {
                            @Override
                            public Map<String, Object> apply(MessageResponse message) {
                                return message.getOutput();
                            }
                        }).thenAccept(new CompletableFuture.Action<Map<String, Object>>() {
                    @Override
                    public void accept(Map<String, Object> output) {
                        System.out.println(output);
                    }
                });

                // rx sync
                MessageResponse rxMessageResponse = null;
                try {
                    rxMessageResponse = service.message(sillyWorkspaceID, newMessage).rx().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(rxMessageResponse);
                return null;
            }
        }
}
