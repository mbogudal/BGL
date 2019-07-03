package com.purplestudio.bgl;

import android.support.test.InstrumentationRegistry;

import com.purplestudio.bgl.Graphic.Graphic;
import com.purplestudio.bgl.Models.DrawableObject;

import org.junit.Test;

public class ReferenceTest
{
    private class Reference
    {
        Graphic graphic;

        public Reference(Graphic graphic)
        {
            this.graphic = graphic;
        }

        public void startTransaction()
        {
            graphic.startTransaction(this);
        }

        public DrawableObject front()
        {
            return graphic.front(this);
        }

        public void releaseTransaction()
        {
            graphic.releaseTransaction(this);
        }

    }

    @Test
    public void test() throws Exception
    {
        Graphic graphic = new Graphic(InstrumentationRegistry.getTargetContext(), 1);
        DrawableObject object = new DrawableObject(100, 100, "happy_face");
        Reference r1;
        Reference r2;

        graphic.pushBack(object);

        r1 = new Reference(graphic);
        r2 = new Reference(graphic);

        r1.startTransaction();
        if(r2.front() != null)
            throw new Exception("illegal access");
        r1.releaseTransaction();


    }

}
