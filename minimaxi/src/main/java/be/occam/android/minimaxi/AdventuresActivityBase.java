package be.occam.android.minimaxi;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sven on 21/09/16.
 */
public class AdventuresActivityBase extends Activity {

    protected  ArrayList<Adventure> adventures
            = new ArrayList<Adventure>();

    public void adventuresDownLoaded( Adventure[] adventures ) {

        this.adventures.clear();
        this.adventures.addAll( Arrays.asList( adventures ) );

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
