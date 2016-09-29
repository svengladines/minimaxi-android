package be.occam.android.minimaxi;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import be.occam.android.minimaxi.timing.Timing;

/**
 * Created by sven on 21/09/16.
 */
public class AdventuresActivityBase extends Activity {

    protected final Comparator<Adventure> mostRecentFirst
            = new Comparator<Adventure>() {

        @Override
        public int compare(Adventure a1, Adventure a2 ) {

            String d1 = a1.getDate();
            String d2 = a2.getDate();

            Date date1 = Timing.date( d1 );
            Date date2 = Timing.date( d2 );

            return ( 0 - date1.compareTo( date2 ) );

        }
    };

    protected  ArrayList<Adventure> adventures
            = new ArrayList<Adventure>();

    public void adventuresDownLoaded( Adventure[] adventures ) {

        this.adventures.clear();
        this.adventures.addAll( Arrays.asList( adventures ) );
        Collections.sort( this.adventures, this.mostRecentFirst );

    }

    public void updateDownloadText( String text ) {

        TextView downloadText
                = (TextView) findViewById(R.id.downloadText);

        downloadText.setText( text );

    }

    public ArrayList<Adventure> getAdventures() {
        return adventures;
    }
}
