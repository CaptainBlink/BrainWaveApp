package com.groupx.brainwaveapp;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by CaptainFlint on 24-Mar-15.
 */
public class MainThread extends Thread{

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    float dt;

    public MainThread(SurfaceHolder holder, GamePanel gamePanel) {

        this.surfaceHolder = holder;
        this.gamePanel = gamePanel;
        dt = 0;
    }

    void setRunning(boolean running){

        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;

        while (running) {
            if (!gamePanel.Pause_game){
                long StartDraw = System.currentTimeMillis();
                canvas = null;
                try{
                    canvas =this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        gamePanel.Update(dt);
                        gamePanel.Draw(canvas);
                    }
                }
                finally{
                    if (canvas!=null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                }



                long EndDraw = System.currentTimeMillis();
                dt = (EndDraw-StartDraw)/1000.f;
            }

        }
    }
}

