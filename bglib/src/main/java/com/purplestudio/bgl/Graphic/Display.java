package com.purplestudio.bgl.Graphic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.widget.ImageView;

import com.purplestudio.bgl.AppView;
import com.purplestudio.bgl.Models.DrawableObject;
import com.purplestudio.bgl.Trigger;

import java.util.concurrent.Semaphore;


public class Display extends Sender implements Runnable, Trigger
{
    private boolean triggered;
    private boolean died;
    private Object[] obj;
    private Canvas tmpCanvas;
    private Bitmap tmpBitmap;
    private ImageView tmpView;
    private Context context;
    private Message tmpMsg;
    private Scene scene;
    private AppView appView;
    private Graphic graphic;
    private DrawableObject tmpDrawable;
    private Semaphore mutex;

    public Display(AppView appView, Graphic graphic, Scene scene, Context context)
    {
        mutex = new Semaphore(1);

        acquire();
        this.appView = appView;
        this.graphic = graphic;
        this.scene = scene;
        this.context = context;

        died = false;
        triggered = false;
        mutex.release();
    }

    private void acquire(){
        try
        {
            mutex.acquire();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void setMarginsView(int color)
    {
        acquire();
        obj = new Object[2];
        tmpMsg = injector.obtainMessage();
        Paint paint = new Paint();
        paint.setColor(color);

        tmpBitmap = Bitmap.createBitmap(
                appView.screenWidth,
                appView.screenHeight,
                Bitmap.Config.ARGB_8888
        );

        tmpCanvas = new Canvas(tmpBitmap);

        if (appView.leftMargin > 0)
            tmpCanvas.drawRect(
                    0,
                    0,
                    appView.leftMargin,
                    appView.screenHeight,
                    paint
            );

        if (appView.topMargin > 0)
            tmpCanvas.drawRect(
                    0,
                    0,
                    appView.screenWidth,
                    appView.topMargin,
                    paint
            );

        if (appView.leftMargin + appView.width < appView.screenWidth)
            tmpCanvas.drawRect(
                    appView.leftMargin + appView.width,
                    0,
                    appView.screenWidth,
                    appView.screenHeight,
                    paint
            );

        if (appView.topMargin + appView.height < appView.screenHeight)
            tmpCanvas.drawRect(
                    0,
                    appView.topMargin + appView.height,
                    appView.screenWidth,
                    appView.screenHeight,
                    paint
            );

        tmpView = new ImageView(context);
        tmpView.setImageBitmap(tmpBitmap);
        obj[0] = scene.getMarginsView();
        obj[1] = tmpView;
        tmpMsg.what = InjectionTypes.REPLACE;
        tmpMsg.obj = obj;
        injector.sendMessage(tmpMsg);
        mutex.release();
    }

    @Override
    public void run()
    {
        setMarginsView(Color.rgb(0, 0, 0));
        while (!died)
        {
            if (triggered)
            {
                acquire();
                tmpMsg = injector.obtainMessage();
                obj = new Object[2];
                tmpBitmap = Bitmap.createBitmap(
                        appView.screenWidth,
                        appView.screenHeight,
                        Bitmap.Config.ARGB_8888
                );
                tmpCanvas = new Canvas(tmpBitmap);

                graphic.startTransaction(this);

                while (graphic.front(this) != null)
                {
                    tmpDrawable = graphic.front(this);

                    if(GraphicOperations.visibleObject(tmpDrawable, appView))
                    tmpCanvas.drawBitmap(
                            tmpDrawable.getBitmap(),
                            GraphicOperations.getMatrix(tmpDrawable),
                            null
                    );

                    graphic.pop(this);
                }

                graphic.releaseTransaction(this);

                tmpView = new ImageView(context);
                tmpView.setImageBitmap(tmpBitmap);

                obj[0] = scene.getSceneView();
                obj[1] = tmpView;

                tmpMsg.what = InjectionTypes.REPLACE;
                tmpMsg.obj = obj;

                injector.sendMessage(tmpMsg);

                triggered = false;
                mutex.release();
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
