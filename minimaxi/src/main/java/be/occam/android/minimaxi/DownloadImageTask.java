package be.occam.android.minimaxi;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sven on 9/09/16.
 */
class DownloadImageTask extends AsyncTask<String, String, byte[]> {

    protected final ImageActivity imageActivity;

    public DownloadImageTask(ImageActivity imageActivity) {

        this.imageActivity = imageActivity;

    }

    String result = "";

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(byte[] result) {

        try {

            if (result == null) {
                return;
            }

            imageActivity.imageDownloaded( result );

        } catch( Throwable t ) {
            imageActivity.updateStatusText( t );
        }

        super.onPostExecute( result );


    }

    @Override
    protected void onProgressUpdate(String... values) {

        super.onProgressUpdate(values);

    }

    @Override
    protected void onCancelled(byte[] aVoid) {
        imageActivity.updateStatusText( "c'" );
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        imageActivity.updateStatusText("c");
        super.onCancelled();
    }

    @Override
    protected byte[] doInBackground(String... params) {

        String url = params[ 0 ];

        try {

            RestTemplate restTemplate
                    = new RestTemplate();

            // Add the String message converter
            // restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter() );

            // Make the HTTP GET request, marshaling the response to a String
            restTemplate.getMessageConverters().add( new ByteArrayHttpMessageConverter() );
            byte[] result
                    = restTemplate.getForObject( url.toString(), byte[].class );

            return result;


        } catch (Throwable t) {
            imageActivity.updateStatusText( t );
            return null;
        }

    }


}