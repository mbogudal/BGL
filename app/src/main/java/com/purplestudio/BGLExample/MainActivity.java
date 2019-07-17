package com.purplestudio.BGLExample;

import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.purplestudio.BGL.AppView;
import com.purplestudio.BGL.Graphic.Display;
import com.purplestudio.BGL.Graphic.Graphic;
import com.purplestudio.BGL.Models.Scene;

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
