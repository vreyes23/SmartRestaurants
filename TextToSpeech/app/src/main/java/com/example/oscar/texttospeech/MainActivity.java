package com.example.oscar.texttospeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonListener();
    }
    private void addButtonListener(){
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String inputText = getTextFromEditText();
                Toast.makeText(getApplicationContext(), "Hi! Testing button..." + inputText,
                        Toast.LENGTH_SHORT).show();
                TextToSpeechWebServiceHandler ttswsh = new TextToSpeechWebServiceHandler(getApplicationContext());
                ttswsh.execute(inputText);
            }
        });
    }
    private String getTextFromEditText(){
        EditText editText = (EditText)findViewById(R.id.editText);
        return editText.getText().toString();
    }
}
