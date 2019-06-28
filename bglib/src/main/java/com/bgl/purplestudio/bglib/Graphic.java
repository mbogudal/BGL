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
    private HashMap<String, Bitmap> bitmaps;
    private List<DrawableObject> objects;
    private Context context;
    private Bitmap tmpBitmap;
    private DrawableObject tmpObject;

    Graphic(Context context)
    {
        this.bitmaps = new HashMap<>();
        this.objects = new ArrayList<>();
        this.context = context;
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

    public boolean pushBack(DrawableObject object)
    {
        if (!busy)
        {
            busy = true;
            loadBitmap(object);
            objects.add(object);
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
            tmpObject = objects.get(0);
            busy = false;
            return tmpObject;
        }

        return null;
    }

    public boolean pop()
    {
        if(!busy){
            busy = true;
            objects.remove(0);
            busy = false;
            return true;
        }

        return false;
    }

    public Bitmap getBitmap(DrawableObject object)
    {
        if(!busy){
            busy = true;
            tmpBitmap = bitmaps.get(object.drawable);
            busy = false;
            return tmpBitmap;
        }

        return null;
    }

}
