package purplestudio.BGLExample;

import android.graphics.Color;

import purplestudio.BGL.AppView;
import purplestudio.BGL.Graphic.Display;
import purplestudio.BGL.Graphic.Graphic;
import purplestudio.BGL.Graphic.GraphicOperations;
import purplestudio.BGL.Models.DrawableObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example implements Runnable
{
    Graphic graphic;
    Display display;
    DrawableObject tmpDrawable;
    AppView appView;
    List<DrawableObject> objects;
    int i;

    Example(Display display, Graphic graphic, AppView appView)
    {
        this.graphic = graphic;
        this.display = display;
        this.appView = appView;
        objects = new ArrayList<>();
    }

    void example1()
    {
        tmpDrawable = new DrawableObject(500, 500, "sad_face");
        tmpDrawable.angle = 0;
        tmpDrawable.layerId = 0;
        tmpDrawable.posx = 100;
        tmpDrawable.posy = 400;

        graphic.pushBack(tmpDrawable);
        display.fire();

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    void example3(){
        List<DrawableObject> test = new ArrayList<>();
        long milis;

        for(int i = 0; i < 100000; i++){
            test.add(new DrawableObject(100, 100, "happy_face"));
            test.get(i).setBitmap(graphic.getBitmap("happy_face"));
        }

        //fast copying list
        milis = System.currentTimeMillis();
        graphic.setObjects(test, 0);
        System.out.println("milis:"+(System.currentTimeMillis()-milis));
        display.fire();
    }

    void example2()
    {

        //creating DrawableObject
        tmpDrawable = new DrawableObject(appView.width, 0, "sad_face");
        tmpDrawable.angle = 0;
        tmpDrawable.layerId = 0;
        tmpDrawable.posx = 0;
        tmpDrawable.posy = appView.topMargin;

        objects.add(tmpDrawable);

        display.setMarginsView(Color.rgb(100, 0, 100));
        while (true)
        {

            if (i % 100 == 0)
            {
                tmpDrawable = new DrawableObject(100, 100, "happy_face");
                tmpDrawable.angle = 0;
                tmpDrawable.layerId = 1;
                tmpDrawable.posx = 0;
                tmpDrawable.posy = appView.screenHeight / 2;

                objects.add(tmpDrawable);

                tmpDrawable = new DrawableObject(100, 100, "green_field");
                tmpDrawable.angle = 0;
                tmpDrawable.layerId = 2;
                tmpDrawable.posx = appView.screenWidth / 2;
                tmpDrawable.posy = 0;

                objects.add(tmpDrawable);

            }

            i++;

            for (DrawableObject object : objects
            )
            {
                if (!GraphicOperations.visibleObject(object, appView))
                {
                    objects.set(objects.indexOf(object), null);
                    continue;
                }

                //in every step program should add every object, that you wont to display, into graphic.
                graphic.pushBack(object);

                //something cool staff
                if (object.getDrawable().equals("happy_face"))
                    object.posx++;
                else if (object.getDrawable().equals("green_field"))
                {
                    if (object.angle >= 360)
                        object.angle = 0;
                    else
                        object.angle++;

                    object.setDrawable("red_field");
                    object.posy++;
                }else if (object.getDrawable().equals("red_field"))
                {
                    if (object.angle >= 360)
                        object.angle = 0;
                    else
                        object.angle++;

                    object.setDrawable("green_field");
                    object.posy++;
                } else if (object.getDrawable().equals("sad_face"))
                {
                    if(object.getHeight() < appView.height)
                        object.setHeight(object.getHeight() + 1);
                    else
                        object.setHeight(0);

                }
            }

            objects.removeAll(Collections.singleton(null));

            //triggering displaying graphic
            display.fire();

            try
            {
                Thread.sleep(5);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run()
    {
        example1();
        example3();
        example2();
    }
}
