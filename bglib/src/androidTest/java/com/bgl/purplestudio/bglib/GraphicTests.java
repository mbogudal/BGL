package com.bgl.purplestudio.bglib;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.bgl.purplestudio.bglib.models.DrawableObject;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GraphicTests
{
    Graphic graphic;
    DrawableObject happyFace;
    DrawableObject sadFace;

    GraphicTests(){
        graphic = new Graphic(InstrumentationRegistry.getTargetContext(), 5);
    }

    @Test
    public void pushBack1() throws Exception
    {
        happyFace = new DrawableObject();
        happyFace.angle = 0;
        happyFace.drawable = "happy_face";
        happyFace.posx = 0;
        happyFace.posy = 0;
        happyFace.layerId = 0;
        happyFace.width = 600;
        happyFace.height = 600;

        graphic.pushBack(happyFace);

        if (graphic.front() == null) throw new Exception("grapic front returned null");

        if (!graphic.front().drawable.equals("happy_face"))
            throw new Exception("bad name");

        Log.d("pushBack1", graphic.front().drawable);
    }

    @Test
    public void pushback2() throws Exception
    {

    }
}
