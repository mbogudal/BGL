package com.bgl.purplestudio.bglib;

import android.support.test.InstrumentationRegistry;

import com.bgl.purplestudio.bglib.Graphic.Graphic;
import com.bgl.purplestudio.bglib.Models.DrawableObject;

import org.junit.Test;

public class Transaction2
{
    Graphic graphic;
    DrawableObject tmp;

    @Test
    public void test() throws Exception{
        graphic = new Graphic(InstrumentationRegistry.getTargetContext(), 1);
        tmp = new DrawableObject();
        tmp.drawable = "happy_face";
        tmp.width = 1;
        tmp.height = 10;
        graphic.pushBack(tmp);

        if(graphic.front(this) != null)
            throw new Exception("access granted");

        if(graphic.pop(this))
            throw new Exception("access granted");

        if(graphic.getBitmap(tmp, this) != null)
            throw new Exception("access granted");
    }

}
