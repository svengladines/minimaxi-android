package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        MiniMaxiProperties properties
                    = new MiniMaxiProperties( this );

        if ( properties != null ) {

            String actor
                    = properties.getProperty( Actor.PROPERTY );

            if ( actor == null ) {

                Intent intent = new Intent(this, CodeActivity.class);

                intent.putExtra( Actor.EXTRA, actor );

                startActivity(intent);

            }
            else {

                try {

                    Intent intent = new Intent(this, AdventuresActivity.class);

                    intent.putExtra(Actor.EXTRA, actor);

                    startActivity(intent);
                }
                catch( Throwable e ) {
                    Log.e( "failed to adventurize", e.getMessage() );
                }

            }

        }

    }

}