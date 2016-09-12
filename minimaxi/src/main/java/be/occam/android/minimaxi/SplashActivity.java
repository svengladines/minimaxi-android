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

    final String FILENAME = "minimaxi.properties";
    final String PROPERTY_CODE = "code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


        Properties properties
                    = this.properties();

        if ( properties != null ) {

            String actor
                    = properties.getProperty( Actor.PROPERTY );

            if ( actor == null ) {

                Intent intent = new Intent(this, CodeActivity.class);

                intent.putExtra( Actor.EXTRA, actor );

                startActivity(intent);

            }
            else {

                Intent intent = new Intent(this, WelcomeActivity.class);

                intent.putExtra( Actor.EXTRA, actor );

                startActivity(intent);

            }

        }

    }

    protected boolean createPropertyFile() {

        FileOutputStream fileOutputStream
                = null;

        try {

            fileOutputStream = openFileOutput( FILENAME, Context.MODE_PRIVATE );

        } catch (IOException e) {

            e.printStackTrace();

        }

        return fileOutputStream != null;

    }

    protected FileOutputStream propertyFileForWriting() {

        FileOutputStream fileOutputStream
                = null;

        try {

            fileOutputStream = openFileOutput( FILENAME, Context.MODE_PRIVATE );

        } catch (IOException e) {

            e.printStackTrace();

        }

        return fileOutputStream;

    }

    protected FileInputStream propertyFileForReading() {

        FileInputStream fileInputStream
                = null;

        try {

            fileInputStream = openFileInput( FILENAME );

        } catch (IOException e) {

            e.printStackTrace();

        }

        return fileInputStream;

    }

    protected Properties properties() {

        Properties properties
                = null;

        try {

            FileInputStream fileInputStream
                    = this.propertyFileForReading();

            if (fileInputStream == null) {

                boolean propertiesCreated
                        = this.createPropertyFile();

                if ( propertiesCreated ) {

                    fileInputStream = this.propertyFileForReading();

                }
                else {
                    Log.e("mini", "property file could not be created");
                }

            }

            if (fileInputStream != null) {
                properties = new Properties();
                properties.load( fileInputStream );
            }
            else {
                Log.e("mini", "no fileinput, even after creation");
            }

        }
       catch (IOException e) {

           Log.e( "splash", e.getMessage() );

        }

        return properties;

    }

}