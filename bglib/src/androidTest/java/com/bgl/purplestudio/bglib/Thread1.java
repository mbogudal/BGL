package com.bgl.purplestudio.bglib;

import com.bgl.purplestudio.bglib.Graphic.Graphic;

public class Thread1 implements Runnable
{
    Graphic graphic;
    int objects;

    Thread1(Graphic graphic, int objects){
        this.graphic = graphic;
        this.objects = objects;
    }

    @Override
    public void run()
    {
        graphic.startTransaction(this);
        for(int i = 0; i < objects * 0.10; i++)
            graphic.pop(this);
        graphic.releaseTransaction(this);

    }
}
