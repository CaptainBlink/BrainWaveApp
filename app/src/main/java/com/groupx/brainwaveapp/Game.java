package com.groupx.brainwaveapp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by CaptainFlint on 25-Mar-15.
 */
public class Game extends Activity {

    final static int UPDATE_SCORE = 0;
    final static int DEATH = 1;
    final static int LOSE = 2;


    View pauseButton;
    View PauseMenu;
    View WinDialog;
    View LoseDialog;

    MediaPlayer GameMusic;

    RelativeLayout Rel_main_game;
    Boolean resume = false;
    GamePanel game_panel;
    TextView txt;
    int get_coins = 0;
    int score = 0;

    final Handler handler = new Handler(){

        public void handleMessage(Message msg){

            if (msg.what == UPDATE_SCORE){
                i_get_coin();
            }

            if (msg.what == DEATH){
                postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Message msg = handler.obtainMessage();
                        msg.what = LOSE;
                        handler.sendMessage(msg);

                    }
                }, 3000);
            }

            if (msg.what == LOSE){
                i_lose();
            }

            super.handleMessage(msg);
        }

    };

    protected void i_get_coin() {

        get_coins++;
        score+= 100;
        txt.setText("Score: " + score);
        MediaPlayer mp = MediaPlayer.create(Game.this, R.raw.coin);
        mp.setVolume(0.3f,0.3f);
        mp.start();
        if (get_coins == 10){
            i_win();
        }

    }

    private void i_win() {

        if (GameMusic.isPlaying())
            GameMusic.stop();
        GameMusic = MediaPlayer.create(Game.this, R.raw.victory);
        GameMusic.setVolume(0.3f,0.3f);
        GameMusic.start();
        game_panel.Pause_game = true;
        WinDialog.setVisibility(View.VISIBLE);

    }

    protected void i_lose() {

        if (GameMusic.isPlaying())
            GameMusic.stop();
        GameMusic = MediaPlayer.create(Game.this, R.raw.game_over);
        GameMusic.setVolume(0.1f,0.1f);
        GameMusic.start();
        game_panel.Pause_game = true;
        LoseDialog.setVisibility(View.VISIBLE);

    }

    View.OnClickListener Continue_List = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            PauseMenu.setVisibility(View.GONE);
            pauseButton.setVisibility(View.VISIBLE);
            game_panel.Pause_game = false;

        }
    };

    View.OnClickListener To_Main_Menu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            game_panel.thread.setRunning(false);
            Game.this.finish();

        }
    };

    View.OnClickListener Pause_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            pauseButton.setVisibility(View.GONE);
            PauseMenu.setVisibility(View.VISIBLE);
            game_panel.Pause_game = true;

            // Pause Start

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Rel_main_game = (RelativeLayout) findViewById(R.id.main_game_rl);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        final int heightS = dm.heightPixels;
        final int widthS = dm.widthPixels;

        game_panel = new GamePanel(getApplicationContext(), this, widthS, heightS);
        Rel_main_game.addView(game_panel);

        RelativeLayout RR = new RelativeLayout(this);
        RR.setBackgroundResource(R.mipmap.button1);
        RR.setGravity(Gravity.CENTER);
        Rel_main_game.addView(RR,150,100);
        RR.setX(0);
        txt= new TextView(this);
        Typeface Custom = Typeface.createFromAsset(getAssets(), "orange juice 2.0.ttf");
        txt.setTypeface(Custom);
        txt.setTextColor(Color.YELLOW);
        txt.setText("Score: " + score);
        RR.addView(txt);

        LayoutInflater myInflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        pauseButton = myInflater.inflate(R.layout.pause, null, false);
        pauseButton.setX(widthS - 150);
        pauseButton.setY(0);
        Rel_main_game.addView(pauseButton);
        pauseButton.setOnClickListener(Pause_click);
        pauseButton.getLayoutParams().height = 100;
        pauseButton.getLayoutParams().width = 100;

        PauseMenu = myInflater.inflate(R.layout.pause_menu, null, false);
        Rel_main_game.addView(PauseMenu);
        PauseMenu.setVisibility(View.GONE);

        ImageView Cont = (ImageView) PauseMenu.findViewById(R.id.img_cont);
        ImageView MainMenuTo = (ImageView) PauseMenu.findViewById(R.id.toMain);
        Cont.setOnClickListener(Continue_List);
        MainMenuTo.setOnClickListener(To_Main_Menu);

        WinDialog= myInflater.inflate(R.layout.win, null, false);
        Rel_main_game.addView(WinDialog);
        ImageView Win_to_main = (ImageView) WinDialog.findViewById(R.id.toMain);
        Win_to_main.setOnClickListener(To_Main_Menu);
        WinDialog.setVisibility(View.GONE);

        LoseDialog= myInflater.inflate(R.layout.lose, null, false);
        Rel_main_game.addView(LoseDialog);
        ImageView Lose_to_main = (ImageView) LoseDialog.findViewById(R.id.toMain);
        Lose_to_main.setOnClickListener(To_Main_Menu);
        LoseDialog.setVisibility(View.GONE);

        GameMusic = MediaPlayer.create(Game.this, R.raw.game_sound);
        GameMusic.setVolume(0.3f,0.3f);
        GameMusic.start();

    }

    @Override
    public void onBackPressed() {

        pauseButton.setVisibility(View.GONE);
        PauseMenu.setVisibility(View.VISIBLE);
        game_panel.Pause_game = true;

    }

    @Override
    protected void onStop() {
        if (GameMusic.isPlaying())
                GameMusic.stop();
        super.onStop();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
