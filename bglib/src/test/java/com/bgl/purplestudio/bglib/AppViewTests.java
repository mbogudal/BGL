package com.bgl.purplestudio.bglib;

import org.junit.Test;

public class AppViewTests {

    @Test
    public void dimensions()
    {
        AppView v1 = new AppView(800, 600);
        AppView v2 = new AppView(800, 600);
        AppView v3 = new AppView(600, 800);
        AppView v4 = new AppView(1200, 600);

        try {
            v1.configure(2);}
            catch (Exception e){
                if(e.getMessage().equals(Errors.WIDTHSCALE.getError()))
                    System.out.println("v1 ok");
                else
                    System.out.println(e);
        }

        try {
            v2.configure(0.75);}
        catch (Exception e){
            System.out.println(e);
        }

        try{
            v3.configure(0.75);
        }catch (Exception e){
            if(e.getMessage().equals(Errors.DIMENSIONSCOMPARE.getError()))
                System.out.println("v3 ok");
            else
                System.out.println(e);
        }

        try {
            v4.configure(0.75);}
        catch (Exception e){
            System.out.println(e);
        }

        if(v2.height == 800 && v2.width == 600)
            System.out.println("v2 ok");

        if(v4.height == 800 && v2.width == 600)
            System.out.println("v4 ok");

    }

}
