package com.groupx.brainwaveapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by CaptainFlint on 25-Mar-15.
 */
public class Hero extends Activity {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int Speed;
    private int inc;
    private int ScreenWidth;
    public ArrayList<Bitmap> Booms = null;
    boolean death;

    float animTime=0;
    float totalAnimTime = 1;
    float numFrames;

    private int ScreenHeight;
    float VertSpeed;
    boolean up;
    private Point tl;
    private Point tr;
    private Point bl;
    private Point br;

    public Hero(Bitmap decodeResource, int x, int y, int screeWidth, int screenheight) {

        this.bitmap = decodeResource;
        this.x = x;
        this.y = y;

        Speed=1;
        inc=0;
        death = false;
        ScreenWidth = screeWidth;
        ScreenHeight = screenheight;
        VertSpeed = 0;
    }

    public void setBoomAnimation(ArrayList<Bitmap> animation){

        Booms = new ArrayList<Bitmap>(animation);
        numFrames = Booms.size();

    }

    public void draw(Canvas canvas) {

        if (!death) {
            canvas.drawBitmap(bitmap, x - bitmap.getWidth() / 2, y - bitmap.getHeight() / 2, null);
        } else {
            int index = (int) (animTime / totalAnimTime * numFrames);
            if (index < numFrames)
                canvas.drawBitmap(Booms.get(index), x - bitmap.getWidth() / 2, y - bitmap.getHeight() / 2, null);
        }
    }



    public void update(float dt){

        if (death){
            animTime += dt;
        }
        else
        {
            VertSpeed+=ScreenHeight/2*dt;
            if (up)
                VertSpeed-=ScreenHeight*dt*2;
            y+=VertSpeed*dt;

            if (y - (bitmap.getHeight() / 2)>ScreenWidth)
                y= 0- (bitmap.getHeight() / 2);
        }

    }

    public boolean bump(Point OTL, Point OTR, Point OBR, Point OBL){

        Point TL = new Point(), TR = new Point(), BL = new Point(), BR = new Point();

        ArrayList<Point> PointList = new ArrayList<Point>();
        PointList.add(OTL);
        PointList.add(OTR);
        PointList.add(OBR);
        PointList.add(OBL);

        getPoint(TL,TR,BL,BR);

        for (int i = 0; i<PointList.size(); i++){
            if (BR.x>=PointList.get(i).x)
                if (TL.x<=PointList.get(i).x)
                    if(PointList.get(i).y>=TL.y)
                        if(PointList.get(i).y<=BR.y)
                            return true;
        }

        PointList.clear();
        PointList.add(TL);
        PointList.add(TR);
        PointList.add(BR);
        PointList.add(BL);

        for (int i = 0; i<PointList.size(); i++){
            if (OBR.x>=PointList.get(i).x)
                if (OTL.x<=PointList.get(i).x)
                    if(PointList.get(i).y>=OTL.y)
                        if(PointList.get(i).y<=OBR.y)
                            return true;
        }

        return false;
    }

    private void getPoint(Point TL, Point TR, Point BL, Point BR) {

        TL.x = x-bitmap.getWidth() / 2;
        TL.y = y - bitmap.getHeight() / 2;

        TR.x = x+bitmap.getWidth() / 2;
        TR.y = y - bitmap.getHeight() / 2;

        BL.x = x-bitmap.getWidth() / 2;
        BL.y = y + bitmap.getHeight() / 2;

        BR.x = x+bitmap.getWidth() / 2;
        BR.y = y+bitmap.getHeight() / 2;
    }
}
