package com.bgl.purplestudio.bglib.Graphic;

import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

public class ViewSwaper implements Runnable
{
    private ImageView oldView;
    private ImageView newView;
    private ConstraintLayout csLayout;

    public ViewSwaper(ImageView oldView, ImageView newView, ConstraintLayout csLayout){
        this.oldView = oldView;
        this.newView = newView;
        this.csLayout = csLayout;
    }

    @Override
    public void run()
    {
        if(oldView != null)
            if(oldView.getParent() != null)
                csLayout.removeView(oldView);

        csLayout.addView(newView);

    }
}
