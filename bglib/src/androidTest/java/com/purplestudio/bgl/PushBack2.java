package com.purplestudio.bgl;

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.purplestudio.bgl.Graphic.Graphic;
import com.purplestudio.bgl.Models.DrawableObject;

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
        happyFace = new DrawableObject(600, 600, "happy_face");
        sadFace = new DrawableObject(600, 600, "sad_face");

        happyFace.angle = 0;
        happyFace.posx = 0;
        happyFace.posy = 0;
        happyFace.layerId = 0;

        graphic.pushBack(happyFace);
        graphic.pushBack(sadFace);

        graphic.startTransaction(this);

        if (graphic.front(this) == null)
            throw new Exception("grapic front returned null");

        if(!graphic.front(this).getDrawable().equals("happy_face"))
            throw new Exception("bad name");

        Log.d("pushBack2a", graphic.front(this).getDrawable());

        if(!graphic.pop(this))
            throw  new Exception("error when deleting");

        if (graphic.front(this) == null)
            throw new Exception("grapic front returned null");

        if (!graphic.front(this).getDrawable().equals("sad_face"))
            throw new Exception("bad name");

        Log.d("pushBack2b", graphic.front(this).getDrawable());

        if(!graphic.pop(this))
            throw  new Exception("error when deleting");

        if(graphic.pop(this))
            throw new Exception("List is not empty");

        graphic.releaseTransaction(this);
    }
}
