package com.bgl.purplestudio.bglib.Graphic;

import android.content.Context;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

public class Scene extends Sender
{
    private ImageView sceneView;
    private ConstraintLayout mainView;
    private ImageView marginsView;
    private ImageView interfaceView;

    public Scene(Context context, ConstraintLayout csLayout)
    {
        Object[] arr = new Object[2];
        Message msg = injector.obtainMessage();

        mainView = new ConstraintLayout(context);
        sceneView = new ImageView(context);
        marginsView = new ImageView(context);
        interfaceView = new ImageView(context);

        mainView.addView(sceneView);
        mainView.addView(interfaceView);
        mainView.addView(marginsView);

        arr[0] = csLayout;
        arr[1] = mainView;

        msg.obj = arr;
        msg.what = InjectionTypes.REPLACE;

        injector.sendMessage(msg);

    }

    public ImageView getSceneView()
    {
        return sceneView;
    }

    public ImageView getMarginsView()
    {
        return marginsView;
    }

    public ImageView getInterfaceView()
    {
        return interfaceView;
    }
}
