package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdventureActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adventure);

        Intent intent
                    = getIntent();

        TextView titleText
                = (TextView) findViewById(R.id.titleText);

        TextView descriptionText
                = (TextView) findViewById(R.id.descriptionText);

        descriptionText.setMovementMethod( new ScrollingMovementMethod() );

        TextView dateText
                = (TextView) findViewById( R.id.dateText );

        try {

            final ArrayList<Adventure> adventures
                    = intent.getParcelableArrayListExtra("adventures");

            if (adventures != null) {

                final int index
                        = intent.getIntExtra("index",0 );

                final Adventure adventure
                        = adventures.get( index );

                dateText.setText( adventure.getDate() );
                titleText.setText(String.format("%s", adventure.getTitle()  ));
                descriptionText.setText( adventure.getDescription() );

                final AdventureActivity adventureActivity
                        = this;

                FloatingActionButton loadImageButton
                        = (FloatingActionButton) findViewById( R.id.loadImageButton );

                loadImageButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        try {

                            Intent intent
                                    = new Intent(adventureActivity, ImageActivity.class);

                            intent.putExtra("url", "http://www.debrodders.be/svekke/minimaxi/images/adventures/adventure-001.jpg"  );

                            startActivity(intent);

                        }
                        catch( Throwable e ) {
                            adventureActivity.updateStatusText( "l-" + e.getStackTrace()[ 0 ].getLineNumber() );
                        }

                    }
                });

                FloatingActionButton nextAdventureButton
                        = (FloatingActionButton) findViewById( R.id.nextAdventureButton );

                nextAdventureButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        try {

                            Intent intent
                                    = new Intent(adventureActivity, AdventureActivity.class);

                                intent.putParcelableArrayListExtra("adventures", adventures );
                                intent.putExtra("index", index < ( adventures.size() -1 ) ? index + 1 : 0 );

                                startActivity(intent);

                        }
                        catch( Throwable e ) {
                            adventureActivity.updateStatusText( "l-" + e.getStackTrace()[ 0 ].getLineNumber() );
                        }

                    }
                });



            } else {
                updateStatusText("null");
            }
        }
        catch( Throwable e ) {



        }


    }

    public void updateStatusText( String text ) {

        TextView titleText
                = (TextView) findViewById(R.id.descriptionText);

        titleText.setTextColor( Color.RED );
        titleText.setText( String.format( "e: %s", text ) );

    }

    public void updateStatusText( Throwable t ) {

        StringWriter stringWriter
                = new StringWriter();
        PrintWriter printWriter
                = new PrintWriter( stringWriter );

        t.printStackTrace( printWriter );

        this.updateStatusText( "t-" + stringWriter.toString() );

    }

}
