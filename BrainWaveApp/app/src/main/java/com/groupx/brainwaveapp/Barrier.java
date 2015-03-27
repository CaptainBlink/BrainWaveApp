package com.groupx.brainwaveapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by WASILLL on 27.3.2015 г..
 */
public class Barrier
{

    private Bitmap bitmap;
    private int x;
    private int y;

    Barriermanager BM;
    boolean doit;

    public Barrier(Bitmap center, int x, int y)
    {
        bitmap = center;
        this.x = x;
        this.y = y;
    }

    public void setManager(Barriermanager barriermanager)
    {
        BM = barriermanager;
    }

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
    }

    public void update(float dt, boolean b)
    {
        if (x < -bitmap.getWidth())
        {
            if (b)
            {
                if (Math.abs(BM.TargetY - BM.dpos) < 50)
                    doit = true;
                if ((BM.TargetY == -1) || doit)
                {
                    BM.TargetY = new Random().nextInt(BM.screenH - BM.dl / 2) + BM.dl / 4;
                }
                if (BM.dpos < BM.TargetY)
                    BM.dpos = BM.dpos + new Random().nextInt(15);
                else
                    BM.dpos = BM.dpos - new Random().nextInt(15);
                y = BM.dpos - BM.dl / 2 - bitmap.getHeight() / 2;
            }
            else
            {
                y = BM.dpos + BM.dl / 2 + bitmap.getHeight() / 2;
            }
            x = (int) (x + bitmap.getWidth() * (BM.TopWalls.size() - 1));
        }
        x = (int) (x - BM.game_panel.CharacterSpeed * dt);
    }

    public ArrayList<Point> GetArray()
    {
        Point TL = new Point(), TR = new Point(), BL = new Point(), BR = new Point();
        TL.x = x - bitmap.getWidth() / 2;
        TL.y = y - bitmap.getHeight() / 2;
        TR.x = x - bitmap.getWidth() / 2;
        TR.y = y - bitmap.getHeight() / 2;
        BL.x = x - bitmap.getWidth() / 2;
        BL.y = y = bitmap.getHeight() / 2;
        BR.x = x - bitmap.getWidth() / 2;
        BR.y = y - bitmap.getHeight() / 2;
        ArrayList<Point> temp = new ArrayList<Point>();
        temp.add(TL);
        temp.add(TR);
        temp.add(BL);
        temp.add(BR);
        return temp;
    }


}
