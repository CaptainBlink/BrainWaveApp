package com.groupx.brainwaveapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by CaptainFlint on 25-Mar-15.
 */
public class Game extends Activity {
    View pauseButton;
    View PauseMenu;
    RelativeLayout Rel_main_game;
    TextView txt;
    Boolean resume = false;
    GamePanel game_panel;

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
        txt=(TextView)findViewById(R.id.txt);
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
