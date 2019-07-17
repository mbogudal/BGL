package purplestudio.BGL.Models;

import android.graphics.Bitmap;

public class DrawableObject extends Object
{
    public int layerId;
    public double angle;
    private String drawable;
    private int point0X;
    private int point0Y;
    private double scaleX;
    private double scaleY;
    private Bitmap bitmap;

    public DrawableObject(int width, int height, String drawable)
    {
        super.width = width;
        super.height = height;
        this.drawable = drawable;
        setPoint0X();
        setPoint0Y();
    }

    @Override
    public void setHeight(int height)
    {
        this.height = height;
        setPoint0Y();
    }

    @Override
    public void setWidth(int width)
    {
        this.width = width;
        setPoint0X();
    }

    public void setDrawable(String drawable){
        this.drawable = drawable;
    }

    public String getDrawable()
    {
        return drawable;
    }

    public int getPoint0X()
    {
        return point0X;
    }

    private void setPoint0X()
    {
        this.point0X = width / 2;
    }

    public int getPoint0Y()
    {
        return point0Y;
    }

    private void setPoint0Y()
    {
        point0Y = height / 2;
    }

    public double getScaleX()
    {
        return scaleX;
    }

    private void setScaleX()
    {
        scaleX = ( (double) width ) / bitmap.getWidth();
    }

    public double getScaleY()
    {
        return scaleY;
    }

    private void setScaleY()
    {
        scaleY = ( (double) height ) / bitmap.getHeight();
    }

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
        setScaleX();
        setScaleY();
    }

}
