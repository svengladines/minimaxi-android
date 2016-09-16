package be.occam.android.minimaxi;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestDownloadTask {

    @Test
    public void doesItSmoke() throws Exception {

        URL url
                = new URL( "http://www.debrodders.be/svekke/minimaxi/images/adventures/adventure-001.jpg" );

        InputStream content
                = (InputStream) url.openStream();

        Drawable drawable
                = Drawable.createFromStream( content, "src" );

    }
}