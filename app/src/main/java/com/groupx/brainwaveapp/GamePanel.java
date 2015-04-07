package com.groupx.brainwaveapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Point;
import java.util.ArrayList;


/**
 * Created by CaptainFlint on 24-Mar-15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{


    public MainThread thread;
    public boolean Pause_game;
    private Background background;
    private Hero hero;
    private Barriermanager BM;
    private Bonus coin;
    public float CharacterSpeed;
    public int ScreeWidth;
    public int Screenheight;
    public Game game;

    public GamePanel(Context context, Game game, int ScreeWidth, int Screenheight) {
        super(context);
        getHolder().addCallback(this);
        this.game = game;
        thread = new MainThread(getHolder(), this);
        background = new Background(BitmapFactory.decodeResource(getResources(), R.mipmap.game_fon), ScreeWidth , this);
        BM = new Barriermanager(BitmapFactory.decodeResource(getResources(), R.mipmap.block), this);
        BM.setScreen(ScreeWidth, Screenheight);

        hero = new Hero(BitmapFactory.decodeResource(getResources(), R.mipmap.ship), 100, 0, ScreeWidth, Screenheight);
        coin = new Bonus(BitmapFactory.decodeResource(getResources(), R.mipmap.coin), -200, -200);
        ArrayList<Bitmap> animation = new ArrayList<Bitmap>();
        animation.add(BitmapFactory.decodeResource(getResources(), R.mipmap.boom1));
        animation.add(BitmapFactory.decodeResource(getResources(), R.mipmap.boom2));
        animation.add(BitmapFactory.decodeResource(getResources(), R.mipmap.boom3));
        animation.add(BitmapFactory.decodeResource(getResources(), R.mipmap.boom4));
        hero.setBoomAnimation(animation);

        coin.setBarrierManager(BM);
        setFocusable(true);
        CharacterSpeed = ScreeWidth/2.f;
        this.ScreeWidth = ScreeWidth;
        this.Screenheight = Screenheight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_DOWN){
            hero.up = true;
        }
        if (event.getAction()==MotionEvent.ACTION_UP){
            hero.up = false;
        }

        return true;
    }

    void Draw(Canvas canvas){

        if (!Pause_game)
            if (canvas!=null) {
                canvas.drawColor(Color.BLACK);
                background.draw(canvas);
                coin.draw(canvas);
                hero.draw(canvas);
                BM.draw(canvas);
            }
    }

    void Update(float dt) {


        hero.update(dt);
        if (!hero.death)
        {
            background.update(dt);
            coin.update(dt);
            BM.update(dt);
            ArrayList<Point>coin_point = new ArrayList<Point>(coin.getArray());
            if (hero.bump(coin_point.get(0), coin_point.get(1), coin_point.get(2), coin_point.get(3)))
            {
                coin.setX(-200);
                coin.setY(-200);
                Message msg = BM.game_panel.game.handler.obtainMessage();
                msg.what = 0;
                BM.game_panel.game.handler.sendMessage(msg);

            }
            for (int i = 0; i < BM.TopWalls.size(); i++) {
                ArrayList<Point>temp = new ArrayList<Point>(BM.TopWalls.get(i).getArray());
                ArrayList<Point>temp2 = new ArrayList<Point>(BM.BottomWalls.get(i).getArray());

                if ((hero.bump(temp.get(0), temp.get(1), temp.get(2), temp.get(3))) || (hero.bump(temp2.get(0), temp2.get(1), temp2.get(2), temp2.get(3))))
                {
                    hero.death = true;
                    MediaPlayer mp = MediaPlayer.create(game, R.raw.boom);
                    mp.setVolume(0.3f,0.3f);
                    mp.start();
                    Message msg = BM.game_panel.game.handler.obtainMessage();
                    msg.what = 1;
                    BM.game_panel.game.handler.sendMessage(msg);
                }
            }
        }
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
