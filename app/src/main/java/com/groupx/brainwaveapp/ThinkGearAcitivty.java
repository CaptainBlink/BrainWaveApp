package com.groupx.brainwaveapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.neurosky.thinkgear.TGDevice;
import com.neurosky.thinkgear.*;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Vasil on 8.4.2015 г..
 */
public class ThinkGearAcitivty extends Activity{

    TGDevice tgDevice;
    BluetoothAdapter btAdapter;
    final boolean rawEnabled = true;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if(btAdapter != null){
            tgDevice = new TGDevice(btAdapter, handler);

        }
    }
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TGDevice.MSG_STATE_CHANGE:
                    switch (msg.arg1) {
                        case TGDevice.STATE_IDLE:
                            break;
                        case TGDevice.STATE_CONNECTING:
                            break;
                        case TGDevice.STATE_CONNECTED:
                            tgDevice.start();
                            break;
                        case TGDevice.STATE_DISCONNECTED:
                            break;
                        case TGDevice.STATE_NOT_FOUND:

                        default:
                            break;
                    }
                    break;
                case TGDevice.MSG_POOR_SIGNAL:
                    Log.v("HelloEEG", "PoorSignal: " + msg.arg1);
                case TGDevice.MSG_ATTENTION:
                    Log.v("HelloEEG", "Attention: " + msg.arg1);
                    break;
                case TGDevice.MSG_RAW_DATA:
                    int rawValue = msg.arg1;
                    break;
                case TGDevice.MSG_EEG_POWER:
                    TGEegPower ep = (TGEegPower)msg.obj;
                    Log.v("HelloEEG", "Delta: " + ep.delta);
                default:
                    break;
            }
        }
    };

        public void doStuff(View view){
            if (tgDevice.getState() !=TGDevice.STATE_CONNECTING && tgDevice.getState() != TGDevice.STATE_CONNECTED)
                tgDevice.connect(rawEnabled);
        }
    public void onDestroy(){
        tgDevice.close();
        super.onDestroy();
    }

}
