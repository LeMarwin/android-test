package com.example.marwin.hellodroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Marwin on 06.01.2015.
 */
public class DrawView extends View {
    public Figure fig;
    int clr;
    public DrawView(Context context)
    {
        super(context);
        fig = new Figure();
        clr = Color.BLUE;
    }

    public DrawView(Context context, int color)
    {
        super(context);
        clr = color;
        fig = new Figure();
    }

    @Override
    protected void  onDraw(Canvas canvas)
    {
        canvas.drawColor(clr);
        Paint pain = new Paint();
        pain.setColor(Color.RED);
        for(final PointF p: fig.getPoints())
        {
            canvas.drawCircle(p.x,p.y,5,pain);
        }
    }

    public void drawHull(Canvas canvas)
    {
    }
}

