package com.purplestudio.BGL;

import android.support.test.InstrumentationRegistry;

import com.purplestudio.BGL.Graphic.Graphic;
import com.purplestudio.BGL.Models.DrawableObject;

import org.junit.Test;

public class Transaction2
{
    Graphic graphic;
    DrawableObject tmp;

    @Test
    public void test() throws Exception{
        graphic = new Graphic(InstrumentationRegistry.getTargetContext(), 1);
        tmp = new DrawableObject(1, 10, "happy_face");
        graphic.pushBack(tmp);

        if(graphic.front(this) != null)
            throw new Exception("access granted");

        if(graphic.pop(this))
            throw new Exception("access granted");

    }

}
