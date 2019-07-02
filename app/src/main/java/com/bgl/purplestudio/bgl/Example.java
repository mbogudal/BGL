package com.bgl.purplestudio.bgl;

import com.bgl.purplestudio.bglib.Graphic.Display;
import com.bgl.purplestudio.bglib.Graphic.Graphic;
import com.bgl.purplestudio.bglib.Models.DrawableObject;

import java.util.ArrayList;
import java.util.List;

public class Example implements Runnable
{
    Graphic graphic;
    Display display;
    DrawableObject tmpDrawable;
    List<DrawableObject> objects;
    int i;

    Example(Display display, Graphic graphic){
        this.graphic = graphic;
        this.display = display;
        objects = new ArrayList<>();
    }

    void example1(){
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

    void example2(){
        while(true){

            if(i % 100 == 0)
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

            for (DrawableObject object: objects
            )
            {
                graphic.pushBack(object);
                System.out.println(object.posy + "/"+object.drawable);
                if(object.drawable.equals("happy_face"))
                    object.posx++;
                else
                {
                    object.posy++;

                }
            }

            display.fire();

            try
            {
                Thread.sleep(30);
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
