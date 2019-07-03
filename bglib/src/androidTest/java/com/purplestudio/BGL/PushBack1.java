package com.purplestudio.BGL;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.purplestudio.BGL.Graphic.Graphic;
import com.purplestudio.BGL.Models.DrawableObject;

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

        happyFace = new DrawableObject(600, 600, "happy_face");
        happyFace.angle = 0;
        happyFace.posx = 0;
        happyFace.posy = 0;
        happyFace.layerId = 0;

        graphic.pushBack(happyFace);

        graphic.startTransaction(this);

        if (graphic.front(this) == null)
            throw new Exception("grapic front returned null");

        if (!graphic.front(this).getDrawable().equals("happy_face"))
            throw new Exception("bad name");

        if(happyFace.getBitmap() == null)
            throw new Exception("bitmap not loaded");

        Log.d("pushBack1", graphic.front(this).getDrawable());

        graphic.releaseTransaction(this);
    }
}
