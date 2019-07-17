package com.purplestudio.BGL;

public enum Errors {
    WIDTHSCALE("widthScale is bigger than one"),
    DIMENSIONSCOMPARE("screenHeight is smaller than screenWidth"),
    BITMAPEXIST("Bitmap with that name already exist"),
    DIMENSIONSLAYOUT("layout not initialized or have dimensions equal to 0");

    public String value;

    Errors(String value)
    {
        this.value = value;
    }

}
