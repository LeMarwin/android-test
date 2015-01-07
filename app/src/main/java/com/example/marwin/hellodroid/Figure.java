package com.example.marwin.hellodroid;

import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static com.example.marwin.hellodroid.Orient.*;

/**
 * Created by Marwin on 06.01.2015.
 */

enum Orient{none, left, right};

public class Figure
{
    private ArrayList<PointF> points;
    private ArrayList<PointF> hull;
    public PointF aim;
    public Figure() {
        points = new ArrayList<PointF>();
        hull = new ArrayList<PointF>();
        aim = new PointF();
    }

    public ArrayList<PointF> getPoints() {
        return points;
    }

    public ArrayList<PointF> getHull() {
        return hull;
    }

    private  Orient turn(PointF a, PointF b, PointF c)
    {
        double val = (b.y - a.y) * (c.x - b.x) -
                (b.x - a.x) * (c.y - b.y);
        if(val==0) return none;
        return (val>0)? right : left;
    }

    private  double distSq(PointF a, PointF b)
    {
        return Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2);
    }

    public ArrayList<PointF> createHull()
    {
        hull = new ArrayList<PointF>();
        hull.add(points.get(0));
        int s = 0;
        int q = 1;
        do
        {
            q = (s+1)%points.size();
            for (int i = 0; i < points.size(); i++)
            {
                Orient t = turn(points.get(s), points.get(q), points.get(i));
                if (t == none)
                    if (distSq(points.get(s), points.get(i)) > distSq(points.get(s), points.get(q)))
                        q = i;
                    else {
                    }
                else if (t == left)
                    q = i;
            }
            hull.add(points.get(q));
            s = q;
        } while(s!=0);
        return hull;
    }

    public String strHull()
    {
        return hull.toString();
    }

    public void add(PointF p)
    {
        points.add(new PointF(p.x,p.y));
    }

    void init(int n)
    {
        points.clear();
        Random rand = new Random();
        for(int i=0;i<n;i++)
        {
            points.add(new PointF(rand.nextFloat()*500,rand.nextFloat()*500));
        }
    }

    public boolean check()
    {
        boolean c = false;
        int i, j;
        int n = hull.size();
        PointF a,b;
        for (i = 0, j = n-1; i < n; j = i++)
        {
            a = hull.get(i);
            b = hull.get(j);
            if ( ((a.y>aim.y) != (b.y>aim.y)) &&
                (aim.x < (b.x-a.x) * (aim.y-a.y) / (b.y-a.y) + a.x) )
                c = !c;
        }
        return c;
    }
}
