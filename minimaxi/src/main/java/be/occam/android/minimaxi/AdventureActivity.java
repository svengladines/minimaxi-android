package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
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



                URL url
                        = new URL( "http://www.debrodders.be/svekke/minimaxi/images/adventures/adventure-001.jpg" );

                InputStream content
                        = url.openStream();

                if ( content == null ) {
                    this.updateStatusText( "no inputstream ");
                    return;
                }

                Drawable drawable
                        = Drawable.createFromStream( content, "src" );

                if ( drawable == null ) {
                    this.updateStatusText( "no drawable ");
                    return;
                }

                ImageView imageView
                        = (ImageView) findViewById( R.id.adventureImage );

                if ( imageView == null ) {
                    this.updateStatusText( "no image ");
                    return;
                }

                imageView.setImageDrawable( drawable );
                imageView.setVisibility( View.VISIBLE );
                content.close();

                final AdventureActivity adventureActivity
                        = this;

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
                            adventureActivity.updateStatusText( e.getMessage() );
                        }

                    }
                });

            } else {
                updateStatusText("null");
            }
        }
        catch( Throwable e ) {
            updateStatusText( "e:" + e.getMessage() );
        }


    }

    public void updateStatusText( String text ) {

        TextView titleText
                = (TextView) findViewById(R.id.titleText);

        titleText.setTextColor( Color.RED );
        titleText.setText( String.format( "e: %s", text ) );

    }


}
