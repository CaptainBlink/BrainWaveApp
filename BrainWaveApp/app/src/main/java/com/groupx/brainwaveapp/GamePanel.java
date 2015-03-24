package com.groupx.brainwaveapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by CaptainFlint on 24-Mar-15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{


    public MainThread thread;
    public boolean Pause_game;
    private Background background;
    public float CharacterSpeed;

    public GamePanel(Context context, Game game, int ScreeWidth) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        background = new Background(BitmapFactory.decodeResource(getResources(), R.mipmap.backgroundnight), ScreeWidth , this);
        setFocusable(true);
        CharacterSpeed = ScreeWidth/2.f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    void Draw(Canvas canvas){

        if (!Pause_game)
            if (canvas!=null) {

                background.draw(canvas);
            }
    }

    void Update(float dt){

        background.update(dt);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e){

            }
        }

    }
}
