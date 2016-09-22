package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class WelcomeActivity extends AdventuresActivityBase {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected Adventure[] adventures;

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

        FloatingActionButton firstAdventureButton
                    = (FloatingActionButton) findViewById( R.id.firstAdventureButton );

        final AdventuresActivityBase adventuresActivity
                = this;

        firstAdventureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                    Intent intent
                            = new Intent(adventuresActivity, AdventureActivity.class);

                    adventuresActivity.updateDownloadText( String.format("%s",adventuresActivity.getAdventures().size() ) );

                    if ( adventuresActivity.getAdventures() != null ) {

                        ArrayList<Adventure>
                                 adventures
                                = new ArrayList<>();

                        adventures.addAll( adventuresActivity.getAdventures() );

                        intent.putParcelableArrayListExtra("adventures", adventures );
                        intent.putExtra("index",0);

                        startActivity(intent);
                    }
                    else {
                        adventuresActivity.updateDownloadText( "nl" );
                    }
                }
                catch( Throwable e ) {
                    adventuresActivity.updateDownloadText( e.getMessage() );
                }

            }
        });

        new DownloadTask( downloadText, adventuresActivity ).execute();


    }

    public void adventuresDownLoaded( Adventure[] adventures ) {

        this.adventures = adventures;

        this.updateDownloadText( String.format("%s", adventures.length ) );

        FloatingActionButton firstAdventureButton
                = (FloatingActionButton) findViewById( R.id.firstAdventureButton );

        firstAdventureButton.setVisibility( View.VISIBLE );

    }

}
