package com.bgl.purplestudio.bglib;

public enum Errors {
    WIDTHSCALE("widthScale is bigger than one"),
    DIMENSIONSCOMPARE("screenHeight is smaller than screenWidth");

    public String value;

    Errors(String value)
    {
        this.value = value;
    }

}
