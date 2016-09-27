package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AdventuresActivity extends AdventuresActivityBase {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected AdventuresAdapter adapter
            = new AdventuresAdapter(this, this.adventures);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adventures);

        // Attach the adapter to the recyclerview to populate items
        final RecyclerView adventuresRecyclerView
                = (RecyclerView) findViewById(R.id.viewAdventures);

        adventuresRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items

        adventuresRecyclerView.setLayoutManager( new LinearLayoutManager(this) );

        TextView statusText
                = (TextView) findViewById( R.id.adventuresDownloadStatusText );

        new DownloadTask(  statusText, this ).execute( );

    }

    @Override
    public void adventuresDownLoaded(Adventure[] adventures) {

        super.adventuresDownLoaded(adventures);

        this.adapter.notifyDataSetChanged();

    }
}
