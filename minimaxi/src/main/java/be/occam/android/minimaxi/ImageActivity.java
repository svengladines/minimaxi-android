package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class ImageActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        Intent intent
                = getIntent();

        String mediaURL
                = intent.getStringExtra("mediaURL");

        String url
                = new StringBuilder("http://www.debrodders.be/svekke/minimaxi/media/adventures/images/").append( mediaURL ).toString();

        new DownloadImageTask( this ).execute( url );


    }

    public void imageDownloaded( byte[] rawImage ) {

        try {

            if ( rawImage == null ) {
                this.updateStatusText("n");
                return;
            }

            if ( rawImage != null ) {
                this.showImage( rawImage );
            }


        }
        catch( Throwable e ) {
            this.updateStatusText( e );
        }
    }


    public void showImage( byte[] rawData ) {

        try {

            if (rawData == null) {
                this.updateStatusText("no bytes");
            }

            this.updateStatusText("d");

            ImageView imageView
                    = (ImageView) findViewById(R.id.imageView);

            Bitmap bitmap
                = this.decodeSampledBitmapFromResource( rawData, imageView.getWidth(), imageView.getHeight() );

            if (bitmap == null) {
                this.updateStatusText("no bitmap ");
                return;
            }

            if (imageView == null) {
                this.updateStatusText("no image ");
                return;
            }

            this.updateStatusText( String.format("bitmap loaded: %s", bitmap.getHeight() ) );

            imageView.setImageBitmap( bitmap );
            imageView.invalidate();
            imageView.forceLayout();

            this.updateStatusText( String.format("imageview updated" ) );
        }
        catch( Throwable e ) {
            this.updateStatusText( e );
        }
    }

    public static int calculateInSampleSize( BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(byte[] rawData, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray( rawData, 0, rawData.length, options );

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return  BitmapFactory.decodeByteArray( rawData, 0, rawData.length, options );
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
