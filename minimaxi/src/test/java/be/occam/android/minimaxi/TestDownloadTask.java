package be.occam.android.minimaxi;

import android.os.Build;
import android.widget.TextView;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestDownloadTask {

    @BeforeClass
    public static void initialize() {
        System.setProperty("ro.build.version.sdk", "23");
    }

    @Test
    public void doesItSmoke() {



        DownloadTask downloadTask
                = new DownloadTask( null );

        downloadTask.doInBackground();


    }
}