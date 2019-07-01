package com.bgl.purplestudio.bglib.Graphic;

import android.os.Looper;

public class Sender
{
    protected static ViewInjector injector;

    public Sender(){
        if(injector == null)
            injector = new ViewInjector(Looper.getMainLooper());
    }

}
