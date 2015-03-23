package com.groupx.brainwaveapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.os.SystemClock;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        mChronometer = new Chronometer(this);

        // Set the initial value
        mChronometer.setText("01:10");
        layout.addView(mChronometer);

        Button startButton = new Button(this);
        startButton.setText("Start");
        startButton.setOnClickListener(mStartListener);
        layout.addView(startButton);

        Button stopButton = new Button(this);
        stopButton.setText("Stop");
        stopButton.setOnClickListener(mStopListener);
        layout.addView(stopButton);

        Button resetButton = new Button(this);
        resetButton.setText("Reset");
        resetButton.setOnClickListener(mResetListener);
        layout.addView(resetButton);


        setContentView(R.layout.activity_main);


    }

    private void showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
        Toast.makeText(MainActivity.this, "Elapsed milliseconds: " + elapsedMillis,
                Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
            int stoppedMilliseconds = 0;

            String chronoText = mChronometer.getText().toString();
            String array[] = chronoText.split(":");
            if (array.length == 2) {
                stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
                        + Integer.parseInt(array[1]) * 1000;
            } else if (array.length == 3) {
                stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                        + Integer.parseInt(array[1]) * 60 * 1000
                        + Integer.parseInt(array[2]) * 1000;
            }

            mChronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
            mChronometer.start();
        }
    };

    View.OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.stop();
            showElapsedTime();
        }
    };

    View.OnClickListener mResetListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.setBase(SystemClock.elapsedRealtime());
            showElapsedTime();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
