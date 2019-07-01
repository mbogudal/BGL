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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rect rect = new Rect();
        Window window = this.getWindow();
        AppView appView;
        Graphic graphic = new Graphic(this, 1);
        Scene scene = new Scene(this, (ConstraintLayout) findViewById(R.id.main));
        Display display;
        Thread t1;

        window.getDecorView().getWindowVisibleDisplayFrame(rect);

        appView = new AppView(rect.bottom, rect.right);

        display = new Display(appView, graphic, scene, this);
        t1 = new Thread(display);

        try{
            appView.configure(0.5);
        }catch (Exception e){
            System.out.println(e);
        }
        t1.start();


    }
}
