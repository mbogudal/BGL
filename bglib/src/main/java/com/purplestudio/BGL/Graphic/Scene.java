package com.purplestudio.BGL.Graphic;

import android.content.Context;
import android.os.Message;
import android.support.constraint.ConstraintLayout;

public class Scene extends Sender
{
    private ConstraintLayout sceneView;
    private ConstraintLayout mainView;
    private ConstraintLayout marginsView;
    private ConstraintLayout interfaceView;

    public Scene(Context context, ConstraintLayout csLayout)
    {
        Object[] arr = new Object[2];
        Message msg = injector.obtainMessage();

        mainView = new ConstraintLayout(context);
        sceneView = new ConstraintLayout(context);
        marginsView = new ConstraintLayout(context);
        interfaceView = new ConstraintLayout(context);

        mainView.addView(sceneView);
        mainView.addView(interfaceView);
        mainView.addView(marginsView);

        arr[0] = csLayout;
        arr[1] = mainView;

        msg.obj = arr;
        msg.what = InjectionTypes.REPLACE;

        injector.sendMessage(msg);

    }

    public ConstraintLayout getSceneView()
    {
        return sceneView;
    }

    public ConstraintLayout getMarginsView()
    {
        return marginsView;
    }

    public ConstraintLayout getInterfaceView()
    {
        return interfaceView;
    }
}
