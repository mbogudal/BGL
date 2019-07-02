package com.bgl.purplestudio.bglib.Graphic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bgl.purplestudio.bglib.Paths;
import com.bgl.purplestudio.bglib.Models.DrawableObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Graphic
{
    private int tmpId;
    private int layerCursor;
    private int layersAmount;
    private Object acquirer;
    private HashMap<String, Bitmap> bitmaps;
    private List<List<DrawableObject>> objects;
    private Context context;
    private Bitmap tmpBitmap;
    private DrawableObject tmpObject;
    private static Semaphore mutex;

    public Graphic(Context context, int layersAmount)
    {
        this.context = context;
        this.layersAmount = layersAmount;
        bitmaps = new HashMap<>();
        objects = new ArrayList<>();
        if (mutex == null)
            mutex = new Semaphore(1);

        for (int i = 0; i < layersAmount; i++)
        {
            objects.add(new ArrayList<DrawableObject>());
        }

    }

    private void loadBitmap(DrawableObject object)
    {
        if (!bitmaps.containsKey(object.drawable))
        {

            tmpId = context.getResources().getIdentifier(
                    object.drawable,
                    Paths.DRAWABLE.toString(),
                    context.getPackageName()

            );

            tmpBitmap = BitmapFactory.decodeResource(context.getResources(), tmpId);

            tmpBitmap = Bitmap.createScaledBitmap(
                    tmpBitmap,
                    object.width,
                    object.height,
                    false
            );

            bitmaps.put(object.drawable, tmpBitmap);

        }
    }

    private boolean setLayerCursor()
    {
        while (layerCursor < layersAmount)
        {
            if (objects.get(layerCursor).size() > 0)
                return true;

            layerCursor++;
        }

        layerCursor = 0;

        return false;
    }

    private void acquire()
    {
        try
        {
            mutex.acquire();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public boolean pushBack(DrawableObject object)
    {
        acquire();
        layerCursor = 0;
        loadBitmap(object);
        objects.get(object.layerId).add(object);
        mutex.release();
        return true;
    }

    public DrawableObject front(Object acquirer)
    {
        if(this.acquirer == acquirer)
            if (setLayerCursor())
            {
                tmpObject = objects.get(layerCursor).get(0);
                return tmpObject;
            }

        return null;
    }

    public boolean pop(Object acquirer)
    {
        if(this.acquirer == acquirer)
            if (setLayerCursor())
            {
                objects.get(layerCursor).remove(0);
                return true;
            }

        return false;
    }

    public Bitmap getBitmap(DrawableObject object, Object acquirer)
    {
        if(this.acquirer == acquirer)
        {
            tmpBitmap = bitmaps.get(object.drawable);
            mutex.release();
            return tmpBitmap;
        }

        return null;
    }

    public boolean startTransaction(Object acquirer)
    {
        acquire();
        if(this.acquirer == null)
        {
            this.acquirer = acquirer;
            return true;
        }
        mutex.release();
        return false;
    }

    public boolean releaseTransaction(Object acquirer){
        if(this.acquirer == acquirer){
            this.acquirer = null;
            mutex.release();
            return true;
        }
        return false;
    }

}
