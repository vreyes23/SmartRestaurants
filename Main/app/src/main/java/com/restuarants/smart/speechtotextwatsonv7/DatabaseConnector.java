package com.restuarants.smart.speechtotextwatsonv7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import java.util.Date;

public class DatabaseConnector extends AppCompatActivity {
        private String DB_NAME = "test";
        private String ACCOUNT = "85d7a97e-6a4a-4710-bf33-116d883d77bb-bluemix";
        private String USERNAME = "rryoupperarnotedgenerste";
        private String PASSWORD = "c91a8e3515d96e5eeb3319f80d7ef35733413516";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // setUpWriteButton();
            // setUpReadButton();
        }
    /*
        private void setUpWriteButton() {
           Button button = (Button) findViewById(R.id.writeButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    writeButtonPressed();
                }
            });
        }
        private void setUpReadButton() {
            Button button = (Button) findViewById(R.id.readButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    readButtonPressed();
                }
            });
        }
        private void writeButtonPressed()
        {
            new WriteAsyncTask().execute();
        }
        private void readButtonPressed()
        {
            EditText idEditText = (EditText) findViewById(R.id.idEditText);
            String id = idEditText.getText().toString();
            new ReadAsyncTask().execute(id);
        }
        class WriteAsyncTask extends AsyncTask<Void, Void, CustomerDB>
        {
            @Override
            protected CustomerDB doInBackground(Void... arg0) {
                CustomerDB user = null;
                try {
                    // Create a new CloudantClient instance for account endpoint <ACCOUNT>.cloudant.com
                    CloudantClient client = ClientBuilder.account(ACCOUNT)
                            .username(USERNAME)
                            .password(PASSWORD)
                            .build();
                    // Get a Database instance to interact with. Do not create it if it doesn't already exist
                    Database db = client.database(DB_NAME, false);
                    user = new CustomerDB("Oscar", "Ricaud", new Date(), 18);
                    db.save(user);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                return user;
            }
            @Override
            protected void onPostExecute(CustomerDB user) {
                super.onPostExecute(user);
                EditText responseEditText = (EditText) findViewById(R.id.responseEditText);
                responseEditText.setText("CustomerDB created:\nID:" + user.getId());
            }
        }
        class ReadAsyncTask extends AsyncTask<String, Void, CustomerDB>
        {
            @Override
            protected CustomerDB doInBackground(String... arg0) {
                CustomerDB user = null;
                try {
                    String id = arg0[0];
                    // Create a new CloudantClient instance for account endpoint <ACCOUNT>.cloudant.com
                    CloudantClient client = ClientBuilder.account(ACCOUNT)
                            .username(USERNAME)
                            .password(PASSWORD)
                            .build();
                    // Get a Database instance to interact with. Do not create it if it doesn't already exist
                    Database db = client.database(DB_NAME, false);
                    // Get an ExampleDocument out of the database and deserialize the JSON into a Java type
                    user = db.find(CustomerDB.class, id);
                } catch (Exception e){
                    e.printStackTrace();
                }
                return user;
            }
            @Override
            protected void onPostExecute(CustomerDB user) {
                super.onPostExecute(user);
                EditText responseEditText = (EditText) findViewById(R.id.responseEditText);
                if (user != null) {
                    responseEditText.setText("Read user from DB:\n"
                            + "ID: " + user.getId() + "\n"
                            + "FirstName: " + user.getFirstName() + "\n"
                            + "LastName: " + user.getLastName() + "\n"
                            + "Creation Date: " + user.getCreationDate().toString() + "\n"
                            + "Age: " + user.getAge() + "\n"
                    );
                }
                else
                {
                    responseEditText.setText("CustomerDB not found");
                }
            }
        }
        */
    }
