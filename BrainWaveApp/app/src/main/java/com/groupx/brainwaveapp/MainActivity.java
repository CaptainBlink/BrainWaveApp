package com.groupx.brainwaveapp;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends Activity{

    private ImageView appImageView;
    private Button appButton;
    private Drawable drawable;
    private Random random;
    private Drawable [] drawables = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appImageView = (ImageView) findViewById(R.id.imageView);
        appButton =(Button) findViewById(R.id.imageButton1);

 /*
* Store the location of images inside the array
*/
        drawables = new Drawable[] {
                getResources().getDrawable(R.mipmap.untitled),

        };

        appButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                random = new Random();
                int randomNumber = random.nextInt(drawables.length);
                drawable = drawables[randomNumber];
                appImageView.setImageDrawable(drawable); // set the image to the ImageView
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
