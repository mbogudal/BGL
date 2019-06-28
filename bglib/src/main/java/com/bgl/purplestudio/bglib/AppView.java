package com.bgl.purplestudio.bglib;

public class AppView {
    public int height;
    public int width;
    public int screenHeight;
    public int screenWidth;
    public double widthScale;

    AppView(int screenHeight, int screenWidth)
    {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    private void setDimensions()
    {
        if(screenWidth / screenHeight >= widthScale) {
            height = screenHeight;
            width = (int) (screenHeight * widthScale);
        } else {
            height = (int) (screenWidth / widthScale);
            width = screenWidth;
        }
    }

    public void configure(double widthScale) throws Exception
    {

        if(widthScale > 1) throw new Exception(Errors.WIDTHSCALE.value);
        if(screenHeight < screenWidth) throw  new Exception(Errors.DIMENSIONSCOMPARE.value);

        this.widthScale = widthScale;

        setDimensions();

    }
}
