package com.purplestudio.bglExample;

import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.purplestudio.bgl.AppView;
import com.purplestudio.bgl.Graphic.Display;
import com.purplestudio.bgl.Graphic.Graphic;
import com.purplestudio.bgl.Graphic.Scene;

public class MainActivity extends AppCompatActivity {

    /*
    This example was prepared for phones with screen resolution 800x480
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rect rect = new Rect();
        Window window = this.getWindow();
        AppView appView;
        Graphic graphic = new Graphic(this, 3);
        Scene scene = new Scene(this, (ConstraintLayout) findViewById(R.id.main));
        Display display;
        Example example;
        Thread t1;
        Thread t2;
        window.getDecorView().getWindowVisibleDisplayFrame(rect);

        appView = new AppView(rect.bottom, rect.right);

        display = new Display(appView, graphic, scene, this);
        t1 = new Thread(display);
        t1.start();
        try{
            appView.configure(0.75);
        }catch (Exception e){
            e.printStackTrace();
        }

        example = new Example(display, graphic, appView);

        t2 = new Thread(example);

        t2.start();

    }
}
