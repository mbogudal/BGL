package com.purplestudio.BGL;

public enum Errors {
    WIDTHSCALE("widthScale is bigger than one"),
    DIMENSIONSCOMPARE("screenHeight is smaller than screenWidth"),
    BITMAPEXIST("Bitmap with that name alredy exist");

    public String value;

    Errors(String value)
    {
        this.value = value;
    }

}
