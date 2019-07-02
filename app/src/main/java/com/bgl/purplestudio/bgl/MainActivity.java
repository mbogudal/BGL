package com.bgl.purplestudio.bgl;

import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.bgl.purplestudio.bglib.AppView;
import com.bgl.purplestudio.bglib.Graphic.Display;
import com.bgl.purplestudio.bglib.Graphic.Graphic;
import com.bgl.purplestudio.bglib.Graphic.Scene;
import com.bgl.purplestudio.bglib.Models.DrawableObject;

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
        Graphic graphic = new Graphic(this, 2);
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

        example = new Example(display, graphic);

        t2 = new Thread(example);

        t2.start();

    }
}
