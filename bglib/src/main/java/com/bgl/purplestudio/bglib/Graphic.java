package com.bgl.purplestudio.bglib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bgl.purplestudio.bglib.models.DrawableObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graphic
{
    private boolean busy;
    private int tmpId;
    private int layerCursor;
    private int layersAmount;
    private HashMap<String, Bitmap> bitmaps;
    private List<List<DrawableObject>> objects;
    private Context context;
    private Bitmap tmpBitmap;
    private DrawableObject tmpObject;

    Graphic(Context context, int layersAmount)
    {
        this.bitmaps = new HashMap<>();
        this.objects = new ArrayList<>();
        this.context = context;
        this.layersAmount = layersAmount;

        for (int i = 0; i < layersAmount; i++)
        {
            objects.add(new ArrayList<DrawableObject>());
        }

    }

    private void loadBitmap(DrawableObject object) throws Exception
    {
        if (bitmaps.containsKey(object.drawable)) throw new Exception(Errors.BITMAPEXIST.value);

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

    private boolean setLayerCursor()
    {
        layerCursor = 0;
        while (layerCursor < layersAmount)
        {
            if (objects.get(layerCursor).size() > 0)
                return true;

            layerCursor++;
        }

        return false;
    }

    public boolean pushBack(DrawableObject object) throws Exception
    {
        if (!busy)
        {
            busy = true;
            loadBitmap(object);
            objects.get(object.layerId).add(object);
            busy = false;
            return true;
        }

        return false;
    }

    public DrawableObject front()
    {
        if (!busy)
        {
            busy = true;
            if (setLayerCursor())
            {
                tmpObject = objects.get(layerCursor).get(0);
                busy = false;
                return tmpObject;
            }

            busy = false;
            return null;

        }

        return null;
    }

    public boolean pop()
    {
        if (!busy)
        {
            busy = true;
            if (setLayerCursor())
            {
                objects.get(layerCursor).remove(0);
                busy = false;
                return true;
            }

            busy = false;
            return false;
        }

        return false;
    }

    public Bitmap getBitmap(DrawableObject object)
    {
        if (!busy)
        {
            busy = true;
            tmpBitmap = bitmaps.get(object.drawable);
            busy = false;
            return tmpBitmap;
        }

        return null;
    }

}
