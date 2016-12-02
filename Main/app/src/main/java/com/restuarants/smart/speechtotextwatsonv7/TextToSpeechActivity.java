package com.restuarants.smart.speechtotextwatsonv7;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.content.Context;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class TextToSpeechActivity {
    private final String WEBSERVICE_URL = "https://stream.watsonplatform.net/text-to-speech/api";
    private final String USERNAME = "82261aef-e939-46ec-8fcc-8709abc2fa52";
    private final String PASSWORD = "OdToyCVteYMU";
    private String type = "POST";
    private Context appContext;
    private File convertedFile;
    private String inputText;

    public TextToSpeechActivity(Context appContext) {
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
                TextToSpeech synthesizer = new TextToSpeech();
                synthesizer.setUsernameAndPassword("username", "password");
                synthesizer.setUsernameAndPassword(USERNAME, PASSWORD);
                try {
                    text = inputText;
                    InputStream stream = synthesizer.synthesize(text, Voice.EN_ALLISON, AudioFormat.OGG).execute();
                    File downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    convertedFile = new File(downloadsFolder, "test.ogg");
                    File.createTempFile("convertedFile", ".ogg", downloadsFolder);
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