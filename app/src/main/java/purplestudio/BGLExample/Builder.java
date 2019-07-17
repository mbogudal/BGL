package purplestudio.BGLExample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.view.Window;
import purplestudio.BGL.AppView;
import purplestudio.BGL.Graphic.Display;
import purplestudio.BGL.Graphic.Graphic;
import purplestudio.BGL.Models.Scene;

public class Builder implements Runnable
{
    private Activity activity;
    private Context context;

    public Builder(Activity activity, Context context){
        this.activity = activity;
        this.context = context;
    }


    @Override
    public void run()
    {

        //waiting for layout being displayed
        while (activity.findViewById(R.id.main).getWidth() == 0);

        Rect rect = new Rect();
        Window window = activity.getWindow();
        AppView appView;
        Graphic graphic = new Graphic(context, 3);
        Scene scene = null;
        try
        {
            scene = new Scene(context, (ConstraintLayout) activity.findViewById(R.id.main));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Display display;
        Example example;
        Thread t1;
        Thread t2;
        window.getDecorView().getWindowVisibleDisplayFrame(rect);

        appView = new AppView(rect.bottom, rect.right);

        display = new Display(appView, graphic, scene, context);
        t1 = new Thread(display);
        t1.start();
        try{
            appView.configure(0.75);
        }catch (Exception e){
            e.printStackTrace();
        }

        example = new Example(display, graphic, appView);

        t2 = new Thread(example);

        t2.start();
    }
}
