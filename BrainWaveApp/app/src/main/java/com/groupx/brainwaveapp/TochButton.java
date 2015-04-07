package com.groupx.brainwaveapp;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class TochButton implements OnTouchListener {
    ImageView IM;

    public TochButton(ImageView imageButton) {
        IM = imageButton;
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
             //   IM.setImageResource(R.mipmap.btn_s);
                break;
            case MotionEvent.ACTION_UP:
             //   IM.setImageResource(R.mipmap.btn);
                break;
            case MotionEvent.ACTION_OUTSIDE:
            //    IM.setImageResource(R.mipmap.btn);
                break;
            default:

                break;
        }
        return false;
    }

}