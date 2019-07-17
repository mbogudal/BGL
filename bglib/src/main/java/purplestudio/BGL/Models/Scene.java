package purplestudio.BGL.Models;

import android.content.Context;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

import purplestudio.BGL.Errors;
import purplestudio.BGL.Injection.InjectionTypes;
import purplestudio.BGL.Injection.Sender;

public class Scene extends Sender
{
    private ConstraintLayout sceneView;
    private ConstraintLayout mainView;
    private ConstraintLayout marginsView;
    private ConstraintLayout interfaceView;
    private ImageView sceneImage;

    public Scene(Context context, ConstraintLayout csLayout) throws Exception
    {

        if(csLayout.getWidth() == 0 || csLayout.getHeight() == 0)
            throw new Exception(Errors.DIMENSIONSLAYOUT.value);

        java.lang.Object[] arr = new java.lang.Object[2];
        Message msg = injector.obtainMessage();

        mainView = new ConstraintLayout(context);
        sceneView = new ConstraintLayout(context);
        marginsView = new ConstraintLayout(context);
        interfaceView = new ConstraintLayout(context);
        sceneImage = new ImageView(context);

        setLayoutParams(csLayout, mainView);
        setLayoutParams(csLayout, sceneView);
        setLayoutParams(csLayout, interfaceView);
        setLayoutParams(csLayout, marginsView);
        setLayoutParams(csLayout, sceneImage);


        mainView.addView(sceneView);
        mainView.addView(interfaceView);
        mainView.addView(marginsView);
        sceneView.addView(sceneImage);

        arr[0] = csLayout;
        arr[1] = mainView;

        msg.obj = arr;
        msg.what = InjectionTypes.REPLACE;

        injector.sendMessage(msg);

    }

    private void setLayoutParams(ConstraintLayout parent, View child){
        child.setLayoutParams(
                new ConstraintLayout.LayoutParams(
                        parent.getWidth(),
                        parent.getHeight()
                )
        );
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

    public ImageView getSceneImage()
    {
        return sceneImage;
    }
}
