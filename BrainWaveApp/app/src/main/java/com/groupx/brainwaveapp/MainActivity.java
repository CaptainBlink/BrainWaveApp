package com.groupx.brainwaveapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.os.SystemClock;
import android.widget.Toast;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    Chronometer chrono;
    Button btnStart;
    Button btnStop;
    Button btnReset;
    TextView txt;
    long elapsedTime=0;
    String currentTime="";
    long startTime=SystemClock.elapsedRealtime();
    Boolean resume=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
