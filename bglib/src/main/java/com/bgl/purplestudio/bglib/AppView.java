package com.bgl.purplestudio.bglib;

public class AppView {
    public int height;
    public int width;
    private int screenHeight;
    private int screenWidth;
    private double widthScale;

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

        if(widthScale > 1) throw new Exception(Errors.WIDTHSCALE.getError());
        if(screenHeight < screenWidth) throw  new Exception(Errors.DIMENSIONSCOMPARE.getError());

        this.widthScale = widthScale;

        setDimensions();

    }
}
