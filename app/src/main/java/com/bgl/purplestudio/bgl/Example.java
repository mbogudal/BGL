package com.bgl.purplestudio.bgl;

import android.graphics.Color;

import com.bgl.purplestudio.bglib.AppView;
import com.bgl.purplestudio.bglib.Graphic.Display;
import com.bgl.purplestudio.bglib.Graphic.Graphic;
import com.bgl.purplestudio.bglib.Graphic.GraphicOperations;
import com.bgl.purplestudio.bglib.Models.DrawableObject;

import java.util.ArrayList;
import java.util.Collection;
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
        tmpDrawable = new DrawableObject();
        tmpDrawable.angle = 0;
        tmpDrawable.drawable = "sad_face";
        tmpDrawable.layerId = 0;
        tmpDrawable.width = 500;
        tmpDrawable.height = 500;
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

    void example2()
    {
        display.setMarginsView(Color.rgb(100, 0, 100));
        while (true)
        {

            if (i % 100 == 0)
            {
                tmpDrawable = new DrawableObject();
                tmpDrawable.angle = 0;
                tmpDrawable.drawable = "happy_face";
                tmpDrawable.layerId = 0;
                tmpDrawable.width = 100;
                tmpDrawable.height = 100;
                tmpDrawable.posx = -100;
                tmpDrawable.posy = 400;

                objects.add(tmpDrawable);

                tmpDrawable = new DrawableObject();
                tmpDrawable.angle = 0;
                tmpDrawable.drawable = "green_field";
                tmpDrawable.layerId = 1;
                tmpDrawable.width = 100;
                tmpDrawable.height = 100;
                tmpDrawable.posx = 200;
                tmpDrawable.posy = -100;

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

                graphic.pushBack(object);
                if (object.drawable.equals("happy_face"))
                    object.posx++;
                else
                {
                    if (object.angle >= 360)
                        object.angle = 0;
                    else
                        object.angle++;
                    object.posy++;
                }
            }

            objects.removeAll(Collections.singleton(null));

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
        example2();
    }
}
