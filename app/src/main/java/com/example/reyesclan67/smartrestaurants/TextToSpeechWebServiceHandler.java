package com.example.reyesclan67.smartrestaurants;

/**
 * Created by reyesclan67 on 9/6/2016.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class TextToSpeechWebServiceHandler {

    private final String WEBSERVICE_URL = "https://stream.watsonplatform.net/text-tospeech/api/v1/synthesize";
    private final String USERNAME = "8ae87d82-4195-46b7-90cd-58df78c391ad";
    private final String PASSWORD = "8AWdCe6WHNGR";
    private String type = "POST";
    private Context appContext;
    private File convertedFile;
    private String inputText;
    public TextToSpeechWebServiceHandler(Context appContext) {
        this.appContext = appContext;
    }
    public void execute(String inputText) {
        this.inputText = inputText;
        new TheTask().execute(WEBSERVICE_URL);
    }
    class TheTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            String text = null;
            try {
                com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech service = new com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech();
                service.setUsernameAndPassword(USERNAME, PASSWORD);
                try {
                    text = inputText;
                    InputStream stream = service.synthesize(text, Voice.EN_ALLISON, AudioFormat.OGG_VORBIS).execute();
                    File downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    convertedFile = new File(downloadsFolder, "test.ogg");//File.createTempFile("convertedFile", ".ogg",downloadsFolder);
                    FileOutputStream out = new FileOutputStream(convertedFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = stream.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                    out.close();
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return text;
        }
        public void playFile() {
            try {
                FileInputStream fis = new FileInputStream(convertedFile);
                MediaPlayer mp = new MediaPlayer();
                mp.setDataSource(fis.getFD());
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                Log.e("MEDIA", e.toString());
                e.printStackTrace();
            }
        }//end playFile
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            playFile();
        }
    }
}