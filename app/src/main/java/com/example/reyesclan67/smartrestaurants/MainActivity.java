package com.example.reyesclan67.smartrestaurants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Order myOrder = new Order("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpWriteButton();
        setUpPlaceOrder();
        setUpSpeechButton();
    }

    private void setUpSpeechButton(){
        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = myOrder.toString();
                Toast.makeText(getApplicationContext(), "Hi! Your input: " + inputText, Toast.LENGTH_SHORT).show();
                TextToSpeechWebServiceHandler ttswsh = new TextToSpeechWebServiceHandler(getApplicationContext());
                ttswsh.execute(inputText);
            }
        });
    }

    private void setUpWriteButton() {
        Button button = (Button) findViewById(R.id.dbButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeButtonPressed();
            }
        });
    }

    private void writeButtonPressed()
    {
        DataRetriever database = new DataRetriever();
        Toast.makeText(getApplicationContext(), "Your Order was written to the Database", Toast.LENGTH_SHORT).show();

    }

    private void setUpPlaceOrder(){
        Button button = (Button)findViewById(R.id.orderButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderButtonPressed();
            }
        });
    }
    private void orderButtonPressed(){
        String inputText = getTextFromEditText();
        myOrder.setCustomerName(inputText);
        Toast.makeText(getApplicationContext(), "Your Order was placed", Toast.LENGTH_SHORT).show();
        System.out.println("The Order was placed:"+myOrder);
    }

    private String getTextFromEditText(){
        EditText editText = (EditText) findViewById(R.id.editText);
        return editText.getText().toString();
}



}
