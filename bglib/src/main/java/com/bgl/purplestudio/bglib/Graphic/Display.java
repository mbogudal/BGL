package com.bgl.purplestudio.bglib.Graphic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.Image;
import android.os.Looper;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

import com.bgl.purplestudio.bglib.AppView;
import com.bgl.purplestudio.bglib.Trigger;


public class Display extends Sender implements Runnable, Trigger
{
    private boolean triggered;
    private boolean died;
    private Object tmpObj;
    private Handler uiHandler;
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
        uiHandler = new Handler(Looper.getMainLooper());

        setMarginsView(0);

    }

    private void drawMargin(int x, int y, int w, int h, int color)
    {
        Matrix matrix = new Matrix();

        tmpBitmap = Bitmap.createBitmap(
                w,
                h,
                Bitmap.Config.ARGB_8888
        );

        matrix.postTranslate(x, y);

        tmpBitmap.eraseColor(color);
        tmpCanvas.drawBitmap(
                tmpBitmap,
                matrix,
                null
        );
    }

    public void setMarginsView(int color)
    {
        tmpMsg = injector.obtainMessage();
        tmpObj = new Object[2];

        tmpBitmap = Bitmap.createBitmap(
                appView.screenWidth,
                appView.screenHeight,
                Bitmap.Config.ARGB_8888
        );

        tmpCanvas = new Canvas(tmpBitmap);

        drawMargin(
                0,
                0,
                appView.leftMargin,
                appView.screenHeight,
                color
        );
        drawMargin(
                0,
                0,
                appView.screenWidth,
                appView.topMargin,
                color
        );
        drawMargin(
                appView.leftMargin + appView.width,
                0,
                appView.screenWidth,
                appView.screenHeight,
                color
        );
        drawMargin(
                0,
                appView.topMargin + appView.height,
                appView.screenWidth,
                appView.screenHeight,
                color
        );

        tmpView = new ImageView(context);
        tmpView.setImageBitmap(tmpBitmap);
        tmpMsg.what = InjectionTypes.REPLACE;
        tmpMsg.obj = tmpObj;
        injector.sendMessage(tmpMsg);

    }

    @Override
    public void run()
    {
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
