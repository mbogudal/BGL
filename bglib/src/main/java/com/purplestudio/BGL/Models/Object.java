package com.purplestudio.BGL.Models;

public class Object
{
    public int posx;
    public int posy;
    protected int height;
    protected int width;

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }
}
