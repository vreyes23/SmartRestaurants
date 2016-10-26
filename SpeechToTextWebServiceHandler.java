package com.pooja.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import android.speech.SpeechRecognizer;
public class SpeechToTextWebServiceHandler {
    private final String WEBSERVICE_URL = "https://stream.watsonplatform.net/speech-to-text/api/v1/recognize";
    private final String USERNAME = "6cf58e19-2519-4393-82ba-e1a45157dd59";
    private final String PASSWORD = "pEICSfPnLvSq";
    private String type = "POST";
    private Context appContext;
    private File audioFile;
    private String outputText;
    public SpeechToTextWebServiceHandler(Context appContext) {
        this.appContext = appContext;
    }
    public void execute(String outputText) {
        this.outputText = outputText;
        new TheTask().execute(WEBSERVICE_URL);
    }
    class TheTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            String audio = null;
            try {
                com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText service = new
                        com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText();
                service.setUsernameAndPassword(USERNAME, PASSWORD);

                try {

                    audio = outputText;
                    InputStream stream = service.recognize(audio, SpeechResults.toString(),
                            SpeechModel.EN_UK_BROADBANDMODEL).execute(audio);
                    File downloadsFolder =
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    audioFile = new File(downloadsFolder, "test.ogg");//File.createTempFile("convertedFile", ".ogg",downloadsFolder);
                    FileOutputStream out = new FileOutputStream(audioFile);
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
            return audio;
        }
        public void playFile() {
            try {
                FileInputStream fis = new FileInputStream(audioFile);
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