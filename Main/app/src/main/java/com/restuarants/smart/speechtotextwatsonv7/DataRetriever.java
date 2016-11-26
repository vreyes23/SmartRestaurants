package com.restuarants.smart.speechtotextwatsonv7;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Created by Victor Reyes last updated on 10/23/2016.
 * Smart Restaurants Application
 * For CS465 Applied Cognitive Computing
 */
public class DataRetriever extends AppCompatActivity {

    private String DB_NAME = "test";
    private String ACCOUNT = "85d7a97e-6a4a-4710-bf33-116d883d77bb-bluemix";
    private String USERNAME = "rryoupperarnotedgenerste";
    private String PASSWORD = "c91a8e3515d96e5eeb3319f80d7ef35733413516";
    private LinkedList<String> final_items = new LinkedList<>();
    private String final_ticketNumber = "";
    private String final_name = "";
    // Receive data from the @see ConfirmationActivity, in simpler terms receive the receipt.
    public DataRetriever(){
      //  setFooditems(final_items);
        new WriteAsyncTask().execute();
    }

    public void setFoodItems(String foodItems) {
        final_items.add(foodItems);
    }
    public LinkedList<String> getFoodItems() {
        return final_items;
    }

    public void setTicketnumber(String ticketnumber) {
        final_ticketNumber = final_ticketNumber + ticketnumber;
    }
    public String getTicketNumber(){
        return final_ticketNumber;
    }

    public void setName(String name) {
        final_name = final_name + name;
    }

    public String getName(){
        return final_name;
    }


    //This Class allows to write to the Cloudant Database
    class WriteAsyncTask extends AsyncTask<Void, Void, Order> {
        protected Order doInBackground(Void... arg0) {
            Order order = null; // I don't think we need this.
            System.out.println("IT is inside doinBackground");
            try {
                // Create a new CloudantClient instance for account endpoint <ACCOUNT>.cloudant.com
                CloudantClient client = ClientBuilder.account(ACCOUNT)
                        .username(USERNAME)
                        .password(PASSWORD)
                        .build();
                // Get a Database instance to interact with. Do not create it if it doesn't already exist
                Database db = client.database(DB_NAME, false);
                // Make sure the ticket order is not empty

                    // Retrieve data from @see ConfirmationActivity
                    String name = getName();
                    String ticketID = getTicketNumber();
                    String foodList = getFoodItems().toString();

                    // Convert string to JSON format
                    JSONObject json = new JSONObject();
                    json.put("Customers name", name);
                    json.put("Ticket number", ticketID);
                    json.put("Food list", foodList);
                    // Send data to IBM's bluemix NoSqlL Services
                    db.save(json);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return order; // we don't need this, delete later.
        }
        @Override
        protected void onPostExecute(Order order) {
            super.onPostExecute(order);
        }
    }

    //This Class allows to read from the Cloudant Database
    class ReadAsyncTask extends AsyncTask<String, Void, Order>
    {
        @Override
        protected Order doInBackground(String... arg0) {
            Order order = null;
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
                order = db.find(Order.class,id);
            } catch (Exception e){
                e.printStackTrace();
            }
            return order;
        }
    }
    private String convertToJSON(String name) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "student");
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("information", "test");
        item.put("id", 3);
        item.put("name", "course1");
        array.put(item);
        json.put("course", array);
        name = json.toString();
        return name;
    }
}