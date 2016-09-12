package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdventureActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent
                    = getIntent();
        String actor
                    = intent.getStringExtra( Actor.EXTRA );

        TextView welcomeText
                = (TextView) findViewById(R.id.welcomeText);

        StringBuilder b
            = new StringBuilder( welcomeText.getText() );
        b.append( " " );
        b.append( actor );

        welcomeText.setText( b.toString() );

        setContentView(R.layout.activity_welcome);

    }



}
