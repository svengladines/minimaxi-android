package be.occam.android.minimaxi;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sven on 9/09/16.
 */
class DownloadTask extends AsyncTask<String, String, Adventure[]> {

    protected final TextView callBackView;
    protected final AdventuresActivityBase adventuresActivity;

    public DownloadTask(TextView callBackView, AdventuresActivityBase adventuresActivity ) {

        this.callBackView = callBackView;
        this.adventuresActivity = adventuresActivity;

    }

    String result = "";

    @Override
    protected void onPreExecute() {

        callBackView.setText("...");

    }

    @Override
    protected void onPostExecute(Adventure[] result) {

        try {

            if (result == null) {
                callBackView.setText("e");
            }

            adventuresActivity.adventuresDownLoaded( result );

        } catch( Exception e ) {
            callBackView.setText( e.getMessage() );
        }

        super.onPostExecute( result );


    }

    @Override
    protected void onProgressUpdate(String... values) {

        super.onProgressUpdate(values);

    }

    @Override
    protected void onCancelled(Adventure[] aVoid) {
        callBackView.setText("c");
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        callBackView.setText("c");
        super.onCancelled();
    }

    @Override
    protected Adventure[] doInBackground(String... params) {

        String url = "http://www.debrodders.be/svekke/minimaxi/adventures/peter/adventures.json?t=" + System.currentTimeMillis();

        try {

            RestTemplate restTemplate
                    = new RestTemplate();

            // Add the String message converter
            // restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter() );

            // Make the HTTP GET request, marshaling the response to a String
            Adventure[] result
                    = restTemplate.getForObject(url, Adventure[].class);

            return result;


        } catch (Throwable e) {
            Log.e("HTTP", "Error converting result " + e.toString());
            return new Adventure[] { new Adventure( e.getMessage() ) } ;
        }

    }


}