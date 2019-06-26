package com.bgl.purplestudio.bglib;

public enum Errors {
    WIDTHSCALE("widthScale is bigger than one"),
    DIMENSIONSCOMPARE("screenHeight is smaller than screenWidth");

    private String content;

    Errors(String content)
    {
        this.content = content;
    }

    public String getError(){
        return content;
    }
}
