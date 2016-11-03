package cognitivecomputing.utep.edu.orderv3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;

import java.io.File;

import static cognitivecomputing.utep.edu.orderv3.R.raw.order;


public class MainActivity extends AppCompatActivity {

    private static final String USERNAME = "b535f60e-dae2-4783-9e89-5ecaf85a468c";
    private static final String PASSWORD = "UqbobaXOFPLX";
    private static final String SERVICE_URL = "https://stream.watsonplatform.net/speech-to-text/api" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Plays audio
        MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, order);
        mPlayer.start();

        // Testing paths
        System.out.println("To String: "  + mPlayer.toString() + "\n");
        File audio = new File(mPlayer.toString());
        System.out.println("audio: " + audio.getAbsolutePath());

        // Creditantials
        SpeechToText service = new SpeechToText();
        service.setUsernameAndPassword(USERNAME, PASSWORD);
        service.setEndPoint(SERVICE_URL);

        // This is the section where the audio should be converted to text but I get an error
        // of the constructor does not apply for the file.

        // File audio = new File(mPlayer.toString());
        // SpeechResults results = service.recognize(new File(mPlayer.toString())).execute();
        // System.out.println(results);
        //service.recognize(mPlayer.start());
    }

}
