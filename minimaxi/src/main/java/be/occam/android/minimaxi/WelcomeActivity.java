package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        Intent intent
                    = getIntent();
        String actor
                    = intent.getStringExtra( Actor.EXTRA );

        if ( actor == null ) {
            actor = "Daddy";
        }

        TextView actorText
                = (TextView) findViewById(R.id.textActor);

        actorText.setText( actor );

        TextView downloadText
                = (TextView) findViewById(R.id.downloadText);

        downloadText.setText( "." );

        new DownloadTask( downloadText ).execute();


    }



}
