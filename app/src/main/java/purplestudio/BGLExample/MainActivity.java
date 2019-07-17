package purplestudio.BGLExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    This example was prepared for phones with screen resolution 800x480
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread;
        Builder builder = new Builder(this, this);

        thread = new Thread(builder);
        thread.start();

    }
}
