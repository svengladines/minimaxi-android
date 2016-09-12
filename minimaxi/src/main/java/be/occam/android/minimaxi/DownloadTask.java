package be.occam.android.minimaxi;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by sven on 9/09/16.
 */
class DownloadTask extends AsyncTask<String, String, Void> {

    protected final TextView callBackView;

    public DownloadTask( TextView callBackView ) {
        this.callBackView = callBackView;
    };

    String result = "";

    @Override
    protected void onPreExecute() {

        callBackView.setText("Downloading adventures");

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callBackView.setText("Download complete");
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Void aVoid) {
        callBackView.setText("Download cancelled");
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        callBackView.setText("Download cancelled");
        super.onCancelled();
    }

    @Override
    protected Void doInBackground(String... params) {
        return null;
    }
}
