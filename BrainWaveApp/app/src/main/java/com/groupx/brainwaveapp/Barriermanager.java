package com.groupx.brainwaveapp;

<<<<<<< HEAD
import android.graphics.Bitmap;
import java.util.ArrayList;

/**
 * Created by User on 27.3.2015 г..
 */
public class Barriermanager {
    Bitmap center;
    int charHeight;
    int screenh;
    int Num;
    int dl;
    int dpos;
    int TargetY = -1;

    ArrayList<Barrier> topwalls = null;
    ArrayList<Barrier> bottomwalls = null;

    public Barriermanager(Bitmap decodeResource) {
        center = decodeResource;
    }

    public void setCharHeight(int charHeight) {
        this.charHeight = charHeight;
    }

    public void setScreen(int width, int height)
    {
        screenh = height;
        Num = (width)/center.getWidth()*4;
        topwalls = new ArrayList<Barrier>();
        for (int i = 0; i< Num; i++)
        {
            Barrier bb = new Barrier(center, width+200+center.getWidth()*i, 0);
            bb.setManager(this);
            topwalls.add(bb);
            Barrier bbb = new Barrier(center, width+200+center.getWidth()*i, 0);
            bbb.setManager(this);
            bottomwalls.add(bbb);
        }
        Generate();
   }

    private void Generate() {
        int h = center.getHeight();
        dl = screenh;
        dpos = screenh/2;
        int new_dl = screenh*3/5;
        int l = (dl-new_dl)/Num;
        for (int i = 0; i < Num; i++)
        {
            dl = dl - l;
            h=topwalls.get(i).getBitmap().getHeight()/2;
            topwalls.get(i).setY(dpos-dl/2-h);
            bottomwalls.get(i).setY(dpos+dl/2+h);
        }
    }

    //9:36
=======
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * Created by User on 27.3.2015 г..
 */
public class Barriermanager
{
    Bitmap center;
    int shipHeight;
    int Num;
    int screenH;
    int dl;
    int TargetY =-1;
    int dpos;
    public GamePanel game_panel;
>>>>>>> origin/master
}
