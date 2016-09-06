package thedorkknightrises.lib.jokehandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        textView = (TextView) findViewById(R.id.jokehandler_textview);
        String str = getIntent().getStringExtra("text");
        textView.setText(str);
    }

    public void setDisplayText(String str) {
        textView.setText(str);
    }
}
