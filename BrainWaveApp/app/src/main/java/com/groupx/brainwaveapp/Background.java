package com.groupx.brainwaveapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by CaptainFlint on 24-Mar-15.
 */
public class Background {

    Bitmap BackBitmap;
    int x,y;
    int ScreenWidth;
    int Count_Background;
    GamePanel root_gamepanel;

public Background(Bitmap bitmap, int Screen_w, GamePanel game_panel){

    this.BackBitmap = bitmap;
    this.x = 0;
    this.y = 0;
    this.ScreenWidth = Screen_w;
    Count_Background = ScreenWidth/BackBitmap.getWidth()+1;
    root_gamepanel = game_panel;

}

public void draw(Canvas canvas){
    for (int i = 0; i<Count_Background+1;i++){
            if (canvas!=null){
                canvas.drawBitmap(BackBitmap, BackBitmap.getWidth()*i+x, y, null);
            }
    if (Math.abs(x)>BackBitmap.getWidth())
    {
        x = x + BackBitmap.getWidth();
    }
    }

}

public void update(float dt){

    x = (int) (x - root_gamepanel.CharacterSpeed*dt);
}

}
