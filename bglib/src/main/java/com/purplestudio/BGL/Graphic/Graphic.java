package com.purplestudio.BGL.Graphic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.purplestudio.BGL.Paths;
import com.purplestudio.BGL.Models.DrawableObject;

import java.util.ArrayList;
import java.util.Collections;
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
    private List<DrawableObject>[] objects;
    private Context context;
    private Bitmap tmpBitmap;
    private DrawableObject tmpObject;
    private Semaphore mutex;

    public Graphic(Context context, int layersAmount)
    {
        mutex = new Semaphore(1);

        acquire();
        this.context = context;
        this.layersAmount = layersAmount;
        bitmaps = new HashMap<>();
        objects = new ArrayList[layersAmount];

        for (int i = 0; i < layersAmount; i++)
        {
            objects[i] = new ArrayList<>();
        }
        mutex.release();
    }

    private void loadBitmap(DrawableObject object)
    {
        if (!bitmaps.containsKey(object.getDrawable()))
        {

            tmpId = context.getResources().getIdentifier(
                    object.getDrawable(),
                    Paths.DRAWABLE.toString(),
                    context.getPackageName()

            );

            tmpBitmap = BitmapFactory.decodeResource(context.getResources(), tmpId);

            bitmaps.put(object.getDrawable(), tmpBitmap);
        }

        object.setBitmap(bitmaps.get(object.getDrawable()));

    }

    private boolean setLayerCursor()
    {
        while (layerCursor < layersAmount)
        {
            if (objects[layerCursor].size() > 0)
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
        objects[object.layerId].add(object);
        mutex.release();
        return true;
    }

    public DrawableObject front(Object acquirer)
    {
        if(this.acquirer == acquirer)
            if (setLayerCursor())
            {
                tmpObject = objects[layerCursor].get(0);
                return tmpObject;
            }

        return null;
    }

    public boolean pop(Object acquirer)
    {
        if(this.acquirer == acquirer)
            if (setLayerCursor())
            {
                objects[layerCursor].remove(0);
                return true;
            }

        return false;
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

    public Bitmap getBitmap(String drawable){
        acquire();
        if (!bitmaps.containsKey(drawable))
        {

            tmpId = context.getResources().getIdentifier(
                    drawable,
                    Paths.DRAWABLE.toString(),
                    context.getPackageName()

            );

            tmpBitmap = BitmapFactory.decodeResource(context.getResources(), tmpId);

            bitmaps.put(drawable, tmpBitmap);
        }

        mutex.release();
        return bitmaps.get(drawable);
    }

    public void setObjects(List<DrawableObject> objects, int layer)
    {
        acquire();
        this.objects[layer] = new ArrayList<>(objects);
        mutex.release();
    }
}
