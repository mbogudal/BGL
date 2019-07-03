package com.bgl.purplestudio.bglib;

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.bgl.purplestudio.bglib.Graphic.Graphic;
import com.bgl.purplestudio.bglib.Models.DrawableObject;

import org.junit.Test;

public class PushBack2
{
    Graphic graphic;
    DrawableObject happyFace;
    DrawableObject sadFace;

    @Test
    public void test() throws Exception
    {
        graphic = new Graphic(InstrumentationRegistry.getTargetContext(), 5);
        happyFace = new DrawableObject();
        sadFace = new DrawableObject();

        happyFace.angle = 0;
        happyFace.drawable = "happy_face";
        happyFace.posx = 0;
        happyFace.posy = 0;
        happyFace.layerId = 0;
        happyFace.width = 600;
        happyFace.height = 600;

        sadFace.drawable = "sad_face";
        sadFace.width = 600;
        sadFace.height = 600;

        graphic.pushBack(happyFace);
        graphic.pushBack(sadFace);

        graphic.startTransaction(this);

        if (graphic.front(this) == null)
            throw new Exception("grapic front returned null");

        if(!graphic.front(this).drawable.equals("happy_face"))
            throw new Exception("bad name");

        Log.d("pushBack2a", graphic.front(this).drawable);

        if(!graphic.pop(this))
            throw  new Exception("error when deleting");

        if (graphic.front(this) == null)
            throw new Exception("grapic front returned null");

        if (!graphic.front(this).drawable.equals("sad_face"))
            throw new Exception("bad name");

        Log.d("pushBack2b", graphic.front(this).drawable);

        if(!graphic.pop(this))
            throw  new Exception("error when deleting");

        if(graphic.pop(this))
            throw new Exception("List is not empty");

        graphic.releaseTransaction(this);
    }
}
