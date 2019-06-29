package com.bgl.purplestudio.bglib.Graphic;

import android.graphics.Matrix;

import com.bgl.purplestudio.bglib.models.DrawableObject;

public class GraphicOperations
{
    private static int xp;
    private static int yp;
    private static Matrix tmpMatrix;

    public static Matrix rotate(DrawableObject object)
    {
        tmpMatrix = new Matrix();
        xp = 0;
        yp = 0;

        if (object.angle > 0)
        {

            tmpMatrix.postRotate((float) object.angle);

            xp = (int) (Math.cos(Math.toRadians(object.angle)) * object.width);
            xp = xp - (int) (Math.sin(Math.toRadians(object.angle)) * object.height);

            yp = (int) (Math.cos(Math.toRadians(object.angle)) * object.height);
            yp = yp + (int) (Math.sin(Math.toRadians(object.angle)) * object.width);

            if (xp == object.width)
                xp = 0;
            else
                xp = Math.abs(xp);

            if (yp == object.height)
                yp = 0;
            else
                yp = Math.abs(yp);
        }

        tmpMatrix.postTranslate(
                object.width + xp,
                object.height + yp
        );

        return tmpMatrix;
    }

}
