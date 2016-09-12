package be.occam.android.minimaxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class CodeActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        EditText editText = (EditText) findViewById(R.id.textCode);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    processCode( v );
                    handled = true;
                }
                return handled;
            }
        });

    }


    public void processCode(TextView view) {

        String code
            = view.getText().toString();

        if ( "xxx".equals( code ) ) {

            Intent intent = new Intent(this, WelcomeActivity.class);

            intent.putExtra( Actor.EXTRA, "peter" );

            startActivity(intent);

        }


    }

}
