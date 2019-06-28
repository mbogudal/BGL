package com.bgl.purplestudio.bglib;

import org.junit.Test;

public class AppViewTests
{

    @Test
    public void v1()
    {
        AppView v1 = new AppView(800, 600);

        try
        {
            v1.configure(2);
        } catch (Exception e)
        {
            if (e.getMessage().equals(Errors.WIDTHSCALE.value))
                System.out.println("v1 ok");
            else
                System.out.println(e);
        }

    }

    @Test
    public void v2()
    {
        AppView v2 = new AppView(800, 600);

        try
        {
            v2.configure(0.75);
        } catch (Exception e)
        {
            System.out.println(e);
        }

        if (v2.height == 800 && v2.width == 600)
            System.out.println("v2 ok");
    }

    @Test
    public void v3()
    {
        AppView v3 = new AppView(600, 800);

        try
        {
            v3.configure(0.75);
        } catch (Exception e)
        {
            if (e.getMessage().equals(Errors.DIMENSIONSCOMPARE.value))
                System.out.println("v3 ok");
            else
                System.out.println(e);
        }

    }

    @Test
    public void v4()
    {
        AppView v4 = new AppView(1200, 600);

        try
        {
            v4.configure(0.75);
        } catch (Exception e)
        {
            System.out.println(e);
        }


        if (v4.height == 800 && v4.width == 600)
            System.out.println("v4 ok");
    }

}
