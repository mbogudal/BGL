package com.bgl.purplestudio.bglib;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.bgl.purplestudio.bglib.models.DrawableObject;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PushBack1
{
    Graphic graphic;
    DrawableObject happyFace;

    @Test
    public void test() throws Exception
    {
        graphic = new Graphic(InstrumentationRegistry.getTargetContext(), 5);

        happyFace = new DrawableObject();
        happyFace.angle = 0;
        happyFace.drawable = "happy_face";
        happyFace.posx = 0;
        happyFace.posy = 0;
        happyFace.layerId = 0;
        happyFace.width = 600;
        happyFace.height = 600;

        graphic.pushBack(happyFace);

        graphic.startTransaction(this);

        if (graphic.front(this) == null)
            throw new Exception("grapic front returned null");

        if (!graphic.front(this).drawable.equals("happy_face"))
            throw new Exception("bad name");

        if(graphic.getBitmap(happyFace, this) == null)
            throw new Exception("bitmap not loaded");

        Log.d("pushBack1", graphic.front(this).drawable);

        graphic.releaseTransaction(this);
    }
}
