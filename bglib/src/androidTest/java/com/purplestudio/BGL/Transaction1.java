package com.purplestudio.BGL;

import android.support.test.InstrumentationRegistry;

import com.purplestudio.BGL.Graphic.Graphic;
import com.purplestudio.BGL.Models.DrawableObject;

import org.junit.Test;

public class Transaction1
{
    Graphic graphic;
    DrawableObject tmp;

    @Test
    public void test() throws Exception
    {
        int objects = 500000;
        graphic = new Graphic(InstrumentationRegistry.getTargetContext(), 1);
        Runnable r1 = new Thread1(graphic, objects);
        Runnable r2 = new Thread2(graphic, objects);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        for(int i = 0; i < objects; i++){
            tmp = new DrawableObject(1, 10, "happy_face");
            graphic.pushBack(tmp);
        }

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
