package com.bgl.purplestudio.bglib.Graphic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.bgl.purplestudio.bglib.AppView;
import com.bgl.purplestudio.bglib.Models.DrawableObject;

public class GraphicOperations
{
    private static Matrix tmpMatrix;

    public static Matrix rotate(DrawableObject object)
    {
        tmpMatrix = new Matrix();

        tmpMatrix.setRotate((float) object.angle, (object.width /2), (object.height/2));
        tmpMatrix.postTranslate(object.posx, object.posy);

        return tmpMatrix;
    }

    public static boolean visibleVertice(int x, int y, AppView appView)
    {
        if (x >= appView.leftMargin && x <= appView.leftMargin + appView.width)
            if (y >= appView.topMargin && y <= appView.topMargin + appView.height)
                return true;

        return false;
    }

    public static boolean visibleObject(DrawableObject object, AppView appView)
    {
        if (visibleVertice(object.posx, object.posy, appView))
            return true;
        if (visibleVertice(object.posx, object.posx + object.width, appView))
            return  true;
        if(visibleVertice(object.posx + object.height, object.posy, appView))
            return true;
        if(visibleVertice(object.posx + object.height, object.posy + object.width, appView))
            return true;

        return false;
    }

}
