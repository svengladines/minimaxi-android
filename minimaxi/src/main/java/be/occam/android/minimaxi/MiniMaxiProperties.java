package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sven on 22/09/16.
 */
public class MiniMaxiProperties {

    final String FILENAME = "minimaxi.properties";

    protected final Activity activity;

    public MiniMaxiProperties( Activity activity ) {
        this.activity = activity;
    }

    public String getProperty( String key ) {

        Properties properties
                =  this.properties();

        if ( properties == null ) {
            return null;
        }

        return properties.getProperty( key );

    }

    public void setProperty( String key, String value ) {

        Properties properties
                =  this.properties();

        if ( properties == null ) {
            return;
        }

        properties.setProperty( key, value );
        try {
            properties.store(this.propertyFileForWriting(), "written property");
        }
        catch( Exception e ) {
            throw new RuntimeException( e );
        }

    }

    protected boolean createPropertyFile() {

        FileOutputStream fileOutputStream
                = null;

        try {

            fileOutputStream = activity.openFileOutput( FILENAME, Context.MODE_PRIVATE );

        } catch (IOException e) {

            e.printStackTrace();

        }

        return fileOutputStream != null;

    }

    protected FileOutputStream propertyFileForWriting() {

        FileOutputStream fileOutputStream
                = null;

        try {

            fileOutputStream = this.activity.openFileOutput( FILENAME, Context.MODE_PRIVATE );

        } catch (IOException e) {

            e.printStackTrace();

        }

        return fileOutputStream;

    }

    protected FileInputStream propertyFileForReading() {

        FileInputStream fileInputStream
                = null;

        try {

            fileInputStream = this.activity.openFileInput( FILENAME );

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
