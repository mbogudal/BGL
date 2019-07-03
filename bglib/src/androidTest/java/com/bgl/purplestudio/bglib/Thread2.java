package com.bgl.purplestudio.bglib;

import android.util.Log;

import com.bgl.purplestudio.bglib.Graphic.Graphic;

public class Thread2 implements Runnable
{
    Graphic graphic;
    int objects;

    Thread2(Graphic graphic, int objects){
        this.graphic = graphic;
        this.objects = objects;
    }

    @Override
    public void run()
    {
        graphic.startTransaction(this);
        for(int i = 0; i < objects; i++)
            if(graphic.front(this) == null)
                Log.d("Thread2", "empty list");

        graphic.releaseTransaction(this);

    }
}
