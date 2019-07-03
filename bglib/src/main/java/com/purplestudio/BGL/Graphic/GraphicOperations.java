package com.purplestudio.BGL.Graphic;

import android.graphics.Matrix;

import com.purplestudio.BGL.AppView;
import com.purplestudio.BGL.Models.DrawableObject;

public class GraphicOperations
{
    private static Matrix tmpMatrix;

    public static Matrix getMatrix(DrawableObject object)
    {
        tmpMatrix = new Matrix();

        tmpMatrix.postScale( (float) object.getScaleX(), (float) object.getScaleY());
        tmpMatrix.postRotate( (float) object.angle, object.getPoint0X(), object.getPoint0Y() );
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
        if (visibleVertice(object.posx, object.posx + object.getWidth(), appView))
            return  true;
        if(visibleVertice(object.posx + object.getWidth(), object.posy, appView))
            return true;
        if(visibleVertice(object.posx + object.getWidth(), object.posy + object.getWidth(), appView))
            return true;

        return false;
    }

}
