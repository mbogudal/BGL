package com.purplestudio.BGL.Graphic;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

public class ViewInjector extends Handler
{
    private Object[] obj;

    public ViewInjector(Looper looper){
        super(looper);
    }

    @Override
    public void handleMessage(Message msg)
    {
        obj = (Object[]) msg.obj;

        switch (msg.what)
        {
            case InjectionTypes.REPLACE:
                ((ViewGroup) obj[0]).addView((View) obj[1]);

                for(int i = 0; i < ((ViewGroup) obj[0]).indexOfChild((View) obj[1]) - 1; i++)
                    ((ViewGroup) obj[0]).removeViewAt(i);

                break;

            case InjectionTypes.INSERT:
                ((ViewGroup) obj[0]).addView((View) obj[1]);
                break;

        }

    }

}
