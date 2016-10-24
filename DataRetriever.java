package com.example.reyesclan67.smartrestaurants;

/**
 * Created by Victor Reyes last updated on 10/23/2016.
 *Smart Restaurants Application
 *For CS465 Applied Cognitive Computing
 */

import android.os.AsyncTask;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;


public class DataRetriever {

    private String DB_NAME = "smartrestaurants";
    private String ACCOUNT = "833c1344-ce44-4b24-b461-98b8faec0b65-bluemix";
    private String USERNAME = "thennegencelfinglygodycr";
    private String PASSWORD = "787e88b978422e10f4e8b25318ec8b4c067bb5d3";



    //This Class allows to write to the Cloudant Database
    class WriteAsyncTask extends AsyncTask<Void, Void, User> {
        protected User doInBackground(Void... arg0) {
            User user = null;
            try {
                // Create a new CloudantClient instance for account endpoint <ACCOUNT>.cloudant.com
                CloudantClient client = ClientBuilder.account(ACCOUNT)
                        .username(USERNAME)
                        .password(PASSWORD)
                        .build();
                // Get a Database instance to interact with. Do not create it if it doesn't already exist
                Database db = client.database(DB_NAME, false);
                user = new User("TestFirstName", "TestLastName", "Category");
                db.save(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        }

    }


    //This Class allows to read from the Cloudant Database
    class ReadAsyncTask extends AsyncTask<String, Void, User>
    {
        @Override
        protected User doInBackground(String... arg0) {
            User user = null;
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
                user = db.find(User.class, id);
            } catch (Exception e){
                e.printStackTrace();
            }
            return user;
        }
    }




}
