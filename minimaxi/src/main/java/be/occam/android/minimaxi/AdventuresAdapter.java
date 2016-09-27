package be.occam.android.minimaxi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sven on 21/09/16.
 */
public class AdventuresAdapter extends RecyclerView.Adapter<AdventuresAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView mediaView;
        protected TextView titleTextView;
        protected TextView descriptionTextView;
        protected String mediaURL;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.adventureTitle);
            descriptionTextView = (TextView) itemView.findViewById(R.id.adventureDescription);
            mediaView = (ImageView) itemView.findViewById(R.id.adventureMedia);

        }

    }

    private List<Adventure> adventureList;

    private Context context;

    public AdventuresAdapter(Context context, List<Adventure> adventures) {
        this.adventureList = adventures;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public AdventuresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View adventureView = inflater.inflate(R.layout.item_adventure, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(adventureView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final AdventuresAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Adventure adventure = this.adventureList.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.titleTextView;
        textView.setText(adventure.getTitle() );
        TextView descriptionTextView = viewHolder.descriptionTextView;
        descriptionTextView.setText(adventure.getDescription() );
        viewHolder.mediaURL = adventure.getMediaURL();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent
                            = new Intent(context, ImageActivity.class);
                    intent.putExtra("mediaURL", viewHolder.mediaURL);

                    context.startActivity(intent);
                }
                catch( Throwable e ) {
                    e.printStackTrace();
                }

            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return adventureList.size();
    }

}
