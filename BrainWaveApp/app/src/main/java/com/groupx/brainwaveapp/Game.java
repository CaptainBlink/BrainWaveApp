package com.groupx.brainwaveapp;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.Button;
import android.view.View;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.apache.http.impl.client.RedirectLocations;


public class Game extends ActionBarActivity {
    View pauseButton;
    View PauseMenu;
    RelativeLayout Rel_main_game;
    Chronometer chrono;
    Button btnStart;
    Button btnStop;
    Button btnReset;
    TextView txt;
    long elapsedTime=0;
    String currentTime="";
    long startTime=SystemClock.elapsedRealtime();
    Boolean resume=false;

    //Pause Menu and button

    OnClickListener Continue_List = new OnClickListener() {
        @Override
        public void onClick(View v) {

            PauseMenu.setVisibility(View.GONE);
            pauseButton.setVisibility(View.VISIBLE);

        }
    };

    OnClickListener To_Main_Menu = new OnClickListener() {
        @Override
        public void onClick(View v) {

            Game.this.finish();

        }
    };

    OnClickListener Pause_click = new OnClickListener() {
        @Override
        public void onClick(View v) {

            pauseButton.setVisibility(View.GONE);
            PauseMenu.setVisibility(View.VISIBLE);

            // Pause Start

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);



        Rel_main_game = (RelativeLayout) findViewById(R.id.main_game_id);

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        final int heightS = dm.heightPixels;
        final int widthS = dm.widthPixels;

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

        ImageView Cont = (ImageView)PauseMenu.findViewById(R.id.img_cont);
        ImageView MainMenuTo = (ImageView)PauseMenu.findViewById(R.id.toMain);
        Cont.setOnClickListener(Continue_List);
        MainMenuTo.setOnClickListener(To_Main_Menu);

        chrono=(Chronometer)findViewById(R.id.chrono);
        btnStart=(Button)findViewById(R.id.btnStart);
        btnStop=(Button)findViewById(R.id.btnStop);
        btnReset=(Button)findViewById(R.id.btnReset);
        txt=(TextView)findViewById(R.id.txt);



    }
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnStart:
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                if (!resume)
                {
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                }
                else
                {
                    chrono.start();
                }

                break;
            case R.id.btnStop:
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                chrono.stop();
                chrono.setText(currentTime);
                resume=true;
                btnStart.setText("Resume");
                break;
            case R.id.btnReset:
                chrono.stop();
                chrono.setText("00:00");
                resume=false;
                btnStop.setEnabled(false);
                break;
        }
    }



    @Override
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
