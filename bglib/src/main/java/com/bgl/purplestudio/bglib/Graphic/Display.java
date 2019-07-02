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
import com.bgl.purplestudio.bglib.Models.DrawableObject;
import com.bgl.purplestudio.bglib.Trigger;


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

    }

    private boolean visibleVertice(int x, int y)
    {
        if (x >= appView.leftMargin && x <= appView.leftMargin + appView.width)
            if (y >= appView.topMargin && y <= appView.topMargin + appView.height)
                return true;

        return false;
    }

    private boolean visibleObject(DrawableObject object)
    {
        if (visibleVertice(object.posx, object.posy))
            return true;
        if (visibleVertice(object.posx, object.posx + object.width))
            return  true;
        if(visibleVertice(object.posx + object.height, object.posy))
            return true;
        if(visibleVertice(object.posx + object.height, object.posy + object.width))
            return true;

        return false;
    }

    @Override
    public void run()
    {
        setMarginsView(Color.rgb(0, 0, 0));
        while (!died)
        {
            if (triggered)
            {
                tmpMsg = injector.obtainMessage();
                obj = new Object[2];
                tmpBitmap = Bitmap.createBitmap(
                        appView.width,
                        appView.height,
                        Bitmap.Config.ARGB_8888
                );
                tmpCanvas = new Canvas(tmpBitmap);

                graphic.startTransaction(this);

                while (graphic.front(this) != null)
                {
                    tmpDrawable = graphic.front(this);

                    graphic.pop(this);

                    if(visibleObject(tmpDrawable))
                    tmpCanvas.drawBitmap(
                            graphic.getBitmap(tmpDrawable, this),
                            GraphicOperations.rotate(tmpDrawable),
                            null
                    );
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
