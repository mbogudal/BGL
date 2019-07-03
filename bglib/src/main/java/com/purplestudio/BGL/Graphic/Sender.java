package com.purplestudio.BGL.Graphic;

import android.os.Looper;

public abstract class Sender
{
    protected static ViewInjector injector;

    public Sender(){
        if(injector == null)
            injector = new ViewInjector(Looper.getMainLooper());
    }

}
