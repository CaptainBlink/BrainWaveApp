package com.groupx.brainwaveapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by CaptainFlint on 07-Apr-15.
 */
public class Barriermanager {

    Bitmap center;
    int heroHeight;
    int Num;
    int screenH;
    int dl;
    int TargetY = -1;
    int dpos;
    public GamePanel game_panel;


    ArrayList<Barier> TopWalls = null;
    ArrayList<Barier>  BottomWalls = null;


    public Barriermanager(Bitmap decodeResource, GamePanel game_panel) {

        center = decodeResource;
        this.game_panel = game_panel;

    }

    void setHeroH(int h){
        heroHeight = h;
    }

    public void setScreen(int width, int height) {
        screenH = height;
        Num = (width)/center.getWidth()+4;
        TopWalls = new ArrayList<Barier>();
        BottomWalls = new ArrayList<Barier>();
        for (int i = 0; i<Num+1; i++){
            Barier BB = new Barier(center, width+200+center.getWidth()*i, 0);
            BB.setManager(this);
            TopWalls.add(BB);
            Barier BBB = new Barier(center, width+200+center.getWidth()*i, 0);
            BBB.setManager(this);
            BottomWalls.add(BBB);
        }
        Generate();
    }

    private void Generate() {

        int h = center.getHeight()/2;
        dl = screenH;
        dpos =screenH/2;
        int new_dl = screenH*3/5;
        int inc =  (dl-new_dl)/Num;
        for (int i = 0; i<Num+1; i++){
            dl = dl - inc;
            h =TopWalls.get(i).getBitmap().getHeight()/2;
            TopWalls.get(i).setY(dpos -dl/2-h);
            BottomWalls.get(i).setY(dpos +dl/2+h);

        }

    }
<<<<<<< HEAD:app/src/main/java/com/groupx/brainwaveapp/Barriermanager.java

    public void draw(Canvas canvas){

=======
   /* public void draw(Canvas canvas){
>>>>>>> origin/master:BrainWaveApp/app/src/main/java/com/groupx/brainwaveapp/Barriermanager.java
        for (int i=0;i<Num+1; i++){
            TopWalls.get(i).draw(canvas);
            BottomWalls.get(i).draw(canvas);
        }

    }

    public void update(float dt){

        for (int i=0;i<Num+1; i++){
            TopWalls.get(i).update(dt, true);
            BottomWalls.get(i).update(dt, false);
        }
<<<<<<< HEAD:app/src/main/java/com/groupx/brainwaveapp/Barriermanager.java

    }

}
=======
    }*/

    //I'm not sure are these methods correct!
    //P.S. Reka please check them from the tutorial :)
}
>>>>>>> origin/master:BrainWaveApp/app/src/main/java/com/groupx/brainwaveapp/Barriermanager.java
