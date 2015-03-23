package com.groupx.brainwaveapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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

    RelativeLayout Btn;
    ImageView ImageButton;
    TextView txt;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Btn = (RelativeLayout) findViewById(R.id.btn_start);
        ImageButton = (ImageView) findViewById(R.id.img_btn1);
        txt = (TextView) findViewById(R.id.text_start);

        Typeface Custom = Typeface.createFromAsset(getAssets(), "orange juice 2.0.ttf");
        txt.setTypeface(Custom);

        Btn.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        Btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainMenu.this, Game.class);
                startActivity(myIntent);

            }
        });


    }
}
