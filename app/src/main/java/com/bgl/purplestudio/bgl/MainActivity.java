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
        Thread t1;
        DrawableObject tmpDrawable;

        window.getDecorView().getWindowVisibleDisplayFrame(rect);

        appView = new AppView(rect.bottom, rect.right);

        display = new Display(appView, graphic, scene, this);
        t1 = new Thread(display);

        try{
            appView.configure(0.75);
        }catch (Exception e){
            System.out.println(e);
        }
        t1.start();

        tmpDrawable = new DrawableObject();
        tmpDrawable.angle = 0;
        tmpDrawable.drawable = "happy_face";
        tmpDrawable.layerId = 0;
        tmpDrawable.width = 100;
        tmpDrawable.height = 100;
        tmpDrawable.posx = 400;
        tmpDrawable.posy = 400git;

        graphic.pushBack(tmpDrawable);

        tmpDrawable = new DrawableObject();
        tmpDrawable.angle = 0;
        tmpDrawable.drawable = "green_field";
        tmpDrawable.layerId = 1;
        tmpDrawable.width = 100;
        tmpDrawable.height = 100;
        tmpDrawable.posx = 200;
        tmpDrawable.posy = 200;

        graphic.pushBack(tmpDrawable);

        display.fire();

    }
}
