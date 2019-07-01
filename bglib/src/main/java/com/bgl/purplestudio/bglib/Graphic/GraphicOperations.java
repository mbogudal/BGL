package com.bgl.purplestudio.bglib.Graphic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.bgl.purplestudio.bglib.Models.DrawableObject;

public class GraphicOperations
{
    private static int xp;
    private static int yp;
    private static Matrix tmpMatrix;

    public static Matrix rotate(DrawableObject object)
    {
        tmpMatrix = new Matrix();
        xp = object.posx;
        yp = object.posy;

        if (object.angle > 0)
        {
            tmpMatrix.postRotate((float) object.angle);

            xp = (int) (Math.cos(Math.toRadians(object.angle)) * object.width);
            xp = xp - (int) (Math.sin(Math.toRadians(object.angle)) * object.height);

            yp = (int) (Math.cos(Math.toRadians(object.angle)) * object.height);
            yp = yp + (int) (Math.sin(Math.toRadians(object.angle)) * object.width);

            if (xp == object.width)
                xp = object.posx;
            else
                xp = object.posx + Math.abs(xp);

            if (yp == object.height)
                yp = object.posy;
            else
                yp = object.posx + Math.abs(yp);
        }

        tmpMatrix.postTranslate(xp, yp);

        return tmpMatrix;
    }

}
