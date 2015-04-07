package com.groupx.brainwaveapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

/**
 * Created by CaptainFlint on 23-Mar-15.
 */
public class MainMenu extends Activity {

    RelativeLayout Btn1;
    RelativeLayout Btn2;
    ImageView ImageButton1;
    ImageView ImageButton2;
    TextView txt1;
    TextView txt2;
    MediaPlayer menuSound;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Btn1 = (RelativeLayout) findViewById(R.id.btn_start);
        Btn2 = (RelativeLayout) findViewById(R.id.btn_quit);
        ImageButton1 = (ImageView) findViewById(R.id.img_btn1);
        ImageButton2 = (ImageView) findViewById(R.id.img_btn2);
        txt1 = (TextView) findViewById(R.id.text_start);
        txt2 = (TextView) findViewById(R.id.text_quit);

        Typeface Custom = Typeface.createFromAsset(getAssets(), "orange juice 2.0.ttf");
        txt1.setTypeface(Custom);
        txt2.setTypeface(Custom);

        Btn1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        ImageButton1.setImageResource(R.mipmap.button1);

                        break;

                    case MotionEvent.ACTION_UP:

                        ImageButton1.setImageResource(R.mipmap.button1);

                        break;

                    case MotionEvent.ACTION_OUTSIDE:

                        ImageButton1.setImageResource(R.mipmap.button1);

                        break;

                    default:

                        ImageButton1.setImageResource(R.mipmap.button1);

                        break;
                }
                return false;
            }
        });

        Btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                menuSound.stop();
                Intent myIntent = new Intent(MainMenu.this, Game.class);
                startActivity(myIntent);

            }
        });

        Btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);

            }
        });


    }

    @Override
    protected void onStart() {

        menuSound = MediaPlayer.create(MainMenu.this, R.raw.menu_sound);
        menuSound.setVolume(0.3f,0.3f);
        menuSound.start();
        super.onStart();

    }

    @Override
    protected void onStop() {

        if (menuSound.isPlaying())
                menuSound.stop();
        super.onStop();

    }

}
