package com.bgl.purplestudio.bglib.Graphic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Looper;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bgl.purplestudio.bglib.AppView;
import com.bgl.purplestudio.bglib.Trigger;


public class Display extends Sender implements Runnable, Trigger
{
    private boolean triggered;
    private boolean died;
    private Canvas tmpCanvas;
    private Bitmap tmpBitmap;
    private ImageView tmpView;
    private Context context;
    private Message tmpMsg;
    private Scene scene;
    private AppView appView;
    private Graphic graphic;

    public Display(AppView appView, Graphic graphic, Scene scene, Context context)
    {
        this.appView = appView;
        this.graphic = graphic;
        this.scene = scene;
        this.context = context;

        died = false;
        triggered = false;

    }

    public void setMarginsView(int color)
    {
        Object[] obj = new Object[2];
        tmpMsg = injector.obtainMessage();
        Paint paint = new Paint();
        paint.setColor(color);

        tmpBitmap = Bitmap.createBitmap(
                appView.screenWidth,
                appView.screenHeight,
                Bitmap.Config.ARGB_8888
        );

        tmpCanvas = new Canvas(tmpBitmap);

        if(appView.leftMargin > 0)
        {
            tmpCanvas.drawRect(
                    0,
                    0,
                    appView.leftMargin,
                    appView.screenHeight,
                    paint
            );
        }

        if(appView.topMargin > 0)
        {
            tmpCanvas.drawRect(
                    0,
                    0,
                    appView.screenWidth,
                    appView.topMargin,
                    paint
            );
        }

        if(appView.leftMargin + appView.width < appView.screenWidth){
            tmpCanvas.drawRect(
                    appView.leftMargin + appView.width,
                    0,
                    appView.screenWidth,
                    appView.screenHeight,
                    paint
            );
        }

        tmpView = new ImageView(context);
        tmpView.setImageBitmap(tmpBitmap);
        obj[0] = scene.getMarginsView();
        obj[1] = tmpView;
        tmpMsg.what = InjectionTypes.REPLACE;
        tmpMsg.obj = obj;
        injector.sendMessage(tmpMsg);

    }

    @Override
    public void run()
    {
        setMarginsView(Color.rgb(0,0,0));
        while (!died)
        {
            if (triggered)
            {


                triggered = false;
            }
        }
    }

    @Override
    public void fire()
    {
        triggered = true;
    }

    @Override
    public void die()
    {
        died = true;
    }
}
